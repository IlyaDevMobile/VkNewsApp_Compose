package com.ilyakoz.vknewsapp.di

import androidx.lifecycle.ViewModel
import com.ilyakoz.vknewsapp.presentation.comments.CommentsViewModel
import com.ilyakoz.vknewsapp.presentation.main.LoginViewModel
import com.ilyakoz.vknewsapp.presentation.news.NewsFeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {


    @IntoMap
    @ViewModelKey(NewsFeedViewModel::class)
    @Binds
    fun bindNewsFeedViewModel(viewModel: NewsFeedViewModel): ViewModel


    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    @Binds
    fun bindMainViewModel(viewModel: LoginViewModel): ViewModel




}