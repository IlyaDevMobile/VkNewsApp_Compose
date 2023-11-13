package com.ilyakoz.vknewsapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class FeedPost(
    val id : String,
    val communityName : String,
    val publicationDate : String,
    val communityImageUrl: String,
    val contentText: String,
    val contentImageUrl : String?,
    val statistics: List<StatisticItem>,
    val isFavourite: Boolean
) : Parcelable
