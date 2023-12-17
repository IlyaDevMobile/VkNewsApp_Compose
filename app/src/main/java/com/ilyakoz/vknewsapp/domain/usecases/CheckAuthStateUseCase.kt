package com.ilyakoz.vknewsapp.domain.usecases

import com.ilyakoz.vknewsapp.domain.repository.NewsFeedRepository
import javax.inject.Inject

class CheckAuthStateUseCase @Inject constructor(private val repository: NewsFeedRepository) {
    suspend operator fun invoke() {
        repository.checkAuthState()
    }
}