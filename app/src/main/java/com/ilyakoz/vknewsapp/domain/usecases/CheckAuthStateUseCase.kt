package com.ilyakoz.vknewsapp.domain.usecases

import com.ilyakoz.vknewsapp.domain.repository.NewsFeedRepository

class CheckAuthStateUseCase(private val repository: NewsFeedRepository) {
    suspend operator fun invoke() {
        repository.checkAuthState()
    }
}