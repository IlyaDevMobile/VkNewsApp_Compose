package com.ilyakoz.vknewsapp.data.model

import com.google.gson.annotations.SerializedName

data class LikesDto(
    @SerializedName("count") val count: Int,
    @SerializedName("count") val userLikes: Int,

)