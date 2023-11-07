package com.ilyakoz.vknewsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(Screen.KEY_FEED_POST_ID) {
                    type = NavType.IntType
                },
                navArgument(Screen.KEY_CONTENT_TEXT) {
                    type = NavType.StringType
                }


            )) {
            val feedPostId = it.arguments?.getInt(Screen.KEY_FEED_POST_ID) ?: 0
            val contentText = it.arguments?.getString(Screen.KEY_CONTENT_TEXT) ?: ""
            commentsScreeContent(FeedPost(id = feedPostId,contentText = contentText))
        }

    }
}