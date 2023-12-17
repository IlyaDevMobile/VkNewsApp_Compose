package com.ilyakoz.vknewsapp.presentation.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import com.ilyakoz.vknewsapp.data.repository.NewsFeedRepositoryImpl
import com.ilyakoz.vknewsapp.domain.entity.FeedPost
import com.ilyakoz.vknewsapp.domain.usecases.GetCommentsUseCase
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    private val feedPost: FeedPost,
    private val getCommentsUseCase: GetCommentsUseCase

) : ViewModel() {


    val screenState = getCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}