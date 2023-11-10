package com.ilyakoz.vknewsapp.data.network

import com.ilyakoz.vknewsapp.data.model.NewsFeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("newsfeed.getRecommended?v=5.154")
    suspend fun loadMyPosts(
        @Query("access_token") token: String
    ) : NewsFeedResponseDto
}