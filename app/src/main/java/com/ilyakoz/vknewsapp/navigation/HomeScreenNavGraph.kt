package com.ilyakoz.vknewsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import com.ilyakoz.vknewsapp.domain.entity.FeedPost

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
                navArgument(Screen.KEY_FEED_POST) {
                    type = NavType.StringType
                }


            )) {
            val feedPostJson = it.arguments?.getString(Screen.KEY_FEED_POST) ?: ""
            val feedPost = Gson().fromJson<FeedPost>(feedPostJson, FeedPost::class.java)
            commentsScreeContent(feedPost)
        }

    }
}