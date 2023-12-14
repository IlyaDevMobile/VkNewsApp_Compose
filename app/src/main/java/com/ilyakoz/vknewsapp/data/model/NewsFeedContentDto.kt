package com.ilyakoz.vknewsapp.data.model

import com.google.gson.annotations.SerializedName

class NewsFeedContentDto(
    @SerializedName("items") val posts: List<PostDto>,
    @SerializedName("groups") val groups: List<GroupDto>,
    @SerializedName("next_from") val nextFrom: String?,

    )