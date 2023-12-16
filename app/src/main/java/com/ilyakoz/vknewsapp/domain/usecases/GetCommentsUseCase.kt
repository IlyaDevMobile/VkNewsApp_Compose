package com.ilyakoz.vknewsapp.domain.usecases

import com.ilyakoz.vknewsapp.domain.entity.FeedPost
import com.ilyakoz.vknewsapp.domain.entity.PostComment
import com.ilyakoz.vknewsapp.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class GetCommentsUseCase(private val repository: NewsFeedRepository) {
    operator fun invoke(feedPost: FeedPost): StateFlow<List<PostComment>> {
        return repository.getComments(feedPost)
    }
}