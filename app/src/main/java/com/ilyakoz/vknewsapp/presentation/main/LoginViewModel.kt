package com.ilyakoz.vknewsapp.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilyakoz.vknewsapp.data.repository.NewsFeedRepositoryImpl
import com.ilyakoz.vknewsapp.domain.usecases.CheckAuthStateUseCase
import com.ilyakoz.vknewsapp.domain.usecases.GetAuthStateFlowUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val getAuthStateUseCase: GetAuthStateFlowUseCase,
    private val checkAuthStateUseCase: CheckAuthStateUseCase
) : ViewModel() {


    val authState = getAuthStateUseCase()


    fun performAuthResult() {
        viewModelScope.launch {
            checkAuthStateUseCase
        }
    }

}