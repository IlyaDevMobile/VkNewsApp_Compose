package com.ilyakoz.vknewsapp.di

import android.content.Context
import com.ilyakoz.vknewsapp.domain.entity.FeedPost
import com.ilyakoz.vknewsapp.presentation.ViewModelFactory
import com.ilyakoz.vknewsapp.presentation.main.MainActivity
import dagger.BindsInstance
import dagger.Component


@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {


    fun getViewModelFactory() : ViewModelFactory
    fun getCommentsScreenComponentFactory(): CommentsScreenComponent.Factory


    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context,
        ): ApplicationComponent
    }
}