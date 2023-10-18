package com.ilyakoz.vknewsapp.ui

import com.ilyakoz.vknewsapp.domain.FeedPost

sealed class NewsFeedScreenState{

    object Initial: NewsFeedScreenState()

    data class Posts(val posts: List<FeedPost>) : NewsFeedScreenState()
}
