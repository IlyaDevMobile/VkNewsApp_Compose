package com.ilyakoz.vknewsapp.domain.usecases

import com.ilyakoz.vknewsapp.domain.entity.FeedPost
import com.ilyakoz.vknewsapp.domain.repository.NewsFeedRepository

class ChangeLikeStatusUseCase(private val repository: NewsFeedRepository) {
    suspend operator fun invoke(feedPost: FeedPost) {
        repository.changeLikeStatus(feedPost)
    }
}