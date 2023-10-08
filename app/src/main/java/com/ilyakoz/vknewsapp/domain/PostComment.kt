package com.ilyakoz.vknewsapp.domain

import com.ilyakoz.vknewsapp.R

data class PostComment(
    val id : Int,
    val authorName : String = "Author",
    val authorAvatarId: Int = R.drawable.author_avatar,
    val commentText: String = "Long comment text",
    val publicationData : String = "14:00"
)