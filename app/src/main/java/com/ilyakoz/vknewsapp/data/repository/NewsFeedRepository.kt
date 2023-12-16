package com.ilyakoz.vknewsapp.data.repository

import android.app.Application
import android.util.Log
import com.ilyakoz.vknewsapp.data.mapper.NewsFeedMapper
import com.ilyakoz.vknewsapp.data.network.ApiFactory
import com.ilyakoz.vknewsapp.domain.FeedPost
import com.ilyakoz.vknewsapp.domain.PostComment
import com.ilyakoz.vknewsapp.domain.StatisticItem
import com.ilyakoz.vknewsapp.domain.StatisticType
import com.ilyakoz.vknewsapp.extensions.mergeWith
import com.ilyakoz.vknewsapp.domain.AuthState
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn

class NewsFeedRepository(application: Application) {

    private val storage = VKPreferencesKeyValueStorage(application)
    private val token get() = VKAccessToken.restore(storage)
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val nextDataNeededEvents = MutableSharedFlow<Unit>(replay = 1)
    private val refreshedListFlow = MutableSharedFlow<List<FeedPost>>()
    private val loadedListFlow = flow {
        nextDataNeededEvents.emit(Unit)
        nextDataNeededEvents.collect {
            val startFrom = nextFrom

            if (startFrom == null && feedPosts.isNotEmpty()) {
                emit(feedPosts)
                return@collect
            }
            val response = if (startFrom == null) {
                apiService.loadMyPosts(getAccessToken())
            } else {
                apiService.loadMyPosts(getAccessToken(), startFrom)

            }
            nextFrom = response.newsFeedContent.nextFrom
            val posts = mapper.mapResponseToPosts(response)
            _feedPosts.addAll(posts)
            emit(feedPosts)
        }

    }
        .retry() {
            delay(RETRY_TIMEOUT_MILLIS)
            true
        }

    private val apiService = ApiFactory.apiService
    private val mapper = NewsFeedMapper()

    private val _feedPosts = mutableListOf<FeedPost>()
    private val feedPosts: List<FeedPost>
        get() = _feedPosts.toList()

    private var nextFrom: String? = null

    private val checkAuthStateEvents = MutableSharedFlow<Unit>(replay = 1)

    val authStateFlow = flow {
        checkAuthStateEvents.collect {
            val currentToken = token
            val loggedIn = currentToken != null && currentToken.isValid
            val authState = if (loggedIn) AuthState.Authorized else AuthState.NotAuthorized
            emit(authState)
        }
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = AuthState.Initial
    )

    val recommendation: StateFlow<List<FeedPost>> = loadedListFlow
        .mergeWith(refreshedListFlow)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Lazily,
            initialValue = feedPosts
        )

    suspend fun loadNextData() {
        nextDataNeededEvents.emit(Unit)
    }

    private fun getAccessToken(): String {
        val token = token?.accessToken ?: throw IllegalArgumentException("Token is null")
        Log.d("NewsFeedRepository", token.toString())
        return token
    }

    fun getComments(feedPost: FeedPost): Flow<List<PostComment>> = flow {
        val comments = apiService.getComments(
            accessToken = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )
        emit(mapper.mapResponseToComments(comments))
    }.retry {
        delay(RETRY_TIMEOUT_MILLIS)
        true
    }

    suspend fun checkAuthState() {
        checkAuthStateEvents.emit(Unit)
    }


    suspend fun deletePost(feedPost: FeedPost) {
        apiService.ignorePost(
            accessToken = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )
        _feedPosts.remove(feedPost)
        refreshedListFlow.emit(feedPosts)

    }

    suspend fun changeLikeStatus(feedPost: FeedPost) {
        val response = if (feedPost.isLiked) {
            apiService.deleteLike(
                token = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
        } else {
            apiService.addLike(
                token = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
        }
        val newLikesCount = response.likes.count
        val newStatistics = feedPost.statistics.toMutableList().apply {
            removeIf { it.type == StatisticType.LIKES }
            add(StatisticItem(type = StatisticType.LIKES, newLikesCount))
        }
        val newPost = feedPost.copy(statistics = newStatistics, isLiked = !feedPost.isLiked)
        val postIndex = _feedPosts.indexOf(feedPost)
        _feedPosts[postIndex] = newPost
        refreshedListFlow.emit(feedPosts)

    }

    companion object {
        private const val RETRY_TIMEOUT_MILLIS = 3000L
    }
}