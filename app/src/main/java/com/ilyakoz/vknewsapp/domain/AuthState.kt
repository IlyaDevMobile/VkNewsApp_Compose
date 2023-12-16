package com.ilyakoz.vknewsapp.domain

sealed class AuthState {

    object Authorized: AuthState()

    object NotAuthorized : AuthState()
    object Initial : AuthState()

}
