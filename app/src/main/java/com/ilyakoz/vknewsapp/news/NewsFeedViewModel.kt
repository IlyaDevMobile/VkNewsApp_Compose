package com.ilyakoz.vknewsapp.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ilyakoz.vknewsapp.data.repository.NewsFeedRepository
import com.ilyakoz.vknewsapp.domain.FeedPost
import com.ilyakoz.vknewsapp.domain.StatisticItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {


    private val initialState = NewsFeedScreenState.Initial


    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState> = _screenState

    private val repository = NewsFeedRepository(application)

    init {
        _screenState.value = NewsFeedScreenState.Loading
        loadRecommendation()
    }

    private fun loadRecommendation() {
        viewModelScope.launch {
            repository.recommendation
                .filter { it.isNotEmpty() }
                .collect{
                    _screenState.value = NewsFeedScreenState.Posts(posts = it)
                }
        }

    }

    fun loadNextRecommendation() {
        _screenState.value =
            NewsFeedScreenState.Posts(
                posts = repository.feedPosts,
                nextDataIsLoading = true
            )
        loadRecommendation()
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.changeLikeStatus(feedPost)
            _screenState.value = NewsFeedScreenState.Posts(posts = repository.feedPosts)

        }
    }


    fun remove(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.deletePost(feedPost)
            _screenState.value = NewsFeedScreenState.Posts(posts = repository.feedPosts)
        }
    }
}