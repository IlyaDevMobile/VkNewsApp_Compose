package com.ilyakoz.vknewsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilyakoz.vknewsapp.domain.FeedPost
import com.ilyakoz.vknewsapp.domain.StatisticItem

class MainViewModel : ViewModel() {

    private val _feedPost = MutableLiveData(FeedPost())
    val feedPost: LiveData<FeedPost> = _feedPost

    fun updateCount(item: StatisticItem){
        val oldStatistics = feedPost.value?.statistics ?: throw IllegalArgumentException()
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type){
                    oldItem.copy(count = oldItem.count +1)
                } else {
                    oldItem
                }
            }
        }
        _feedPost.value = _feedPost.value?.copy(statistics = newStatistics)
    }
}