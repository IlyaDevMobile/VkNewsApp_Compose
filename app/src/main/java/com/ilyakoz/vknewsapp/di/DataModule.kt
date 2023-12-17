package com.ilyakoz.vknewsapp.di

import android.content.Context
import com.ilyakoz.vknewsapp.data.network.ApiFactory
import com.ilyakoz.vknewsapp.data.network.ApiService
import com.ilyakoz.vknewsapp.data.repository.NewsFeedRepositoryImpl
import com.ilyakoz.vknewsapp.domain.repository.NewsFeedRepository
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface DataModule {
    @ApplicationScope
    @Binds
    fun bindRepository(impl: NewsFeedRepositoryImpl): NewsFeedRepository


    companion object {
        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @ApplicationScope
        @Provides
        fun provideVkStorage(context: Context): VKPreferencesKeyValueStorage {
            return VKPreferencesKeyValueStorage(context)
        }
    }
}