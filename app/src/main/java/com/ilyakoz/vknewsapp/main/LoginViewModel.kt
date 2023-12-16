package com.ilyakoz.vknewsapp.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ilyakoz.vknewsapp.data.repository.NewsFeedRepositoryImpl
import com.ilyakoz.vknewsapp.domain.usecases.CheckAuthStateUseCase
import com.ilyakoz.vknewsapp.domain.usecases.GetAuthStateFlowUseCase
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryImpl(application)

    private val getAuthStateUseCase = GetAuthStateFlowUseCase(repository)
    private val checkAuthStateUseCase = CheckAuthStateUseCase(repository)

    val authState = getAuthStateUseCase()


    fun performAuthResult() {
        viewModelScope.launch {
            checkAuthStateUseCase
        }
    }

}