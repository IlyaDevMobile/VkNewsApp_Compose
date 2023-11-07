package com.ilyakoz.vknewsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ilyakoz.vknewsapp.domain.FeedPost

fun NavGraphBuilder.homeScreenNavGraph(
    newFeedScreenContent: @Composable () -> Unit,
    commentsScreeContent: @Composable (FeedPost) -> Unit

) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route
    ) {


        composable(Screen.NewsFeed.route) {
            newFeedScreenContent()
        }
        composable(Screen.Comments.route) {
           val feedPostId =  it.arguments?.getInt(Screen.KEY_FEED_POST_ID) ?: 0
            commentsScreeContent(FeedPost(id = feedPostId))
        }

    }
}