package com.ilyakoz.vknewsapp.domain.usecases

import com.ilyakoz.vknewsapp.domain.repository.NewsFeedRepository

class LoadNextDataUseCase(private val repository: NewsFeedRepository) {
    suspend operator fun invoke() {
        repository.loadNextData()
    }
}