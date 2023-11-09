package com.ilyakoz.vknewsapp

sealed class AuthState {

    object Authorized: AuthState()

    object NotAuthorized : AuthState()
    object Initial : AuthState()

}
