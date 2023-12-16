package com.ilyakoz.vknewsapp.domain.repository

import com.ilyakoz.vknewsapp.domain.entity.AuthState
import com.ilyakoz.vknewsapp.domain.entity.FeedPost
import com.ilyakoz.vknewsapp.domain.entity.PostComment
import kotlinx.coroutines.flow.StateFlow

interface NewsFeedRepository {

    fun getAuthStateFlow(): StateFlow<AuthState>
    fun getRecommendations(): StateFlow<List<FeedPost>>
    fun getComments(feedPost: FeedPost): StateFlow<List<PostComment>>
    suspend fun deletePost(feedPost: FeedPost)
    suspend fun changeLikeStatus(feedPost: FeedPost)
    suspend fun loadNextData()
    suspend fun checkAuthState()


}