package com.ilyakoz.vknewsapp.domain

data class PostComment(
    val id : Long,
    val authorName : String,
    val authorAvatarUrl: String,
    val commentText: String,
    val publicationData : String 
)