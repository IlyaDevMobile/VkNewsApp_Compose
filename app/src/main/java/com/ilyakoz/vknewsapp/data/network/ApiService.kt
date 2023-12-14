package com.ilyakoz.vknewsapp.data.network

import com.ilyakoz.vknewsapp.data.model.LikesCountResponseDto
import com.ilyakoz.vknewsapp.data.model.NewsFeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("newsfeed.getRecommended?v=5.154")
    suspend fun loadMyPosts(
        @Query("access_token") token: String
    ): NewsFeedResponseDto


    @GET("newsfeed.getRecommended?v=5.154")
    suspend fun loadMyPosts(
        @Query("access_token") token: String,
        @Query("start_from") startFrom: String
    ): NewsFeedResponseDto


    @GET("likes.add?v=5.154")
    suspend fun addLike(
        @Query("access_token") token: String,
        @Query("owner_id") ownerId: Long,
        @Query("item_id") postId: Long
    ) : LikesCountResponseDto

    @GET("likes.delete?v=5.154")
    suspend fun deleteLike(
        @Query("access_token") token: String,
        @Query("owner_id") ownerId: Long,
        @Query("item_id") postId: Long
    ) : LikesCountResponseDto
}

