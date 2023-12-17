package com.ilyakoz.vknewsapp.di

import com.ilyakoz.vknewsapp.domain.entity.FeedPost
import com.ilyakoz.vknewsapp.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Subcomponent


@Subcomponent(
    modules = [CommentsViewModelModule::class]
)
interface CommentsScreenComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance feedPost: FeedPost
        ): CommentsScreenComponent
    }


}