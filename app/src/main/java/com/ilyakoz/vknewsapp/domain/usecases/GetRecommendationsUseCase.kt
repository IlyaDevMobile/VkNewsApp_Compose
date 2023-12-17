package com.ilyakoz.vknewsapp.domain.usecases

import com.ilyakoz.vknewsapp.domain.entity.FeedPost
import com.ilyakoz.vknewsapp.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetRecommendationsUseCase @Inject constructor(private val repository: NewsFeedRepository) {
    operator fun invoke(): StateFlow<List<FeedPost>> {
        return repository.getRecommendations()
    }
}