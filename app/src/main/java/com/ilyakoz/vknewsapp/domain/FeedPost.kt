package com.ilyakoz.vknewsapp.domain

import com.ilyakoz.vknewsapp.R

data class FeedPost(
    val id : Int = 0,
    val communityName : String = "/dev/null",
    val publicationDate : String  = "12:00",
    val avatarResId: Int = R.drawable.avatar,
    val contentText: String = "Здесь могла бы ваша реклама",
    val contentImageResId : Int = R.drawable.avatar,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(type = StatisticType.VIEWS,966),
        StatisticItem(type = StatisticType.SHARES,6),
        StatisticItem(type = StatisticType.COMMENTS,8),
        StatisticItem(type = StatisticType.LIKES,27)
    )
)
