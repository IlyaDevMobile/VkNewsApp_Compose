package com.ilyakoz.vknewsapp.domain.usecases

import com.ilyakoz.vknewsapp.domain.entity.AuthState
import com.ilyakoz.vknewsapp.domain.entity.FeedPost
import com.ilyakoz.vknewsapp.domain.entity.PostComment
import com.ilyakoz.vknewsapp.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow

class GetAuthStateFlowUseCase(private val repository: NewsFeedRepository) {
    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }
}