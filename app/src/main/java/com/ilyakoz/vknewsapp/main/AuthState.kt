package com.ilyakoz.vknewsapp.main

sealed class AuthState {

    object Authorized: AuthState()

    object NotAuthorized : AuthState()
    object Initial : AuthState()

}
