package com.ilyakoz.vknewsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ilyakoz.vknewsapp.domain.FeedPost

class CommentsViewModelFactory(
    private val feedPost: FeedPost
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(feedPost) as T
    }
}