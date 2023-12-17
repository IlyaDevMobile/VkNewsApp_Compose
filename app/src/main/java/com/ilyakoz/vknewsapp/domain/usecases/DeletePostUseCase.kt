package com.ilyakoz.vknewsapp.domain.usecases

import com.ilyakoz.vknewsapp.domain.entity.FeedPost
import com.ilyakoz.vknewsapp.domain.repository.NewsFeedRepository
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(private val repository: NewsFeedRepository) {
    suspend operator fun invoke(feedPost: FeedPost) {
        repository.deletePost(feedPost)
    }
}