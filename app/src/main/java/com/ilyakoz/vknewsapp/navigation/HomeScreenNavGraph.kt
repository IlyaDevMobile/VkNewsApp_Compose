package com.ilyakoz.vknewsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeScreenNavGraph(
    newFeedScreenContent: @Composable () -> Unit,
    commentsScreeContent: @Composable () -> Unit

) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route
    ) {


        composable(Screen.NewsFeed.route) {
            newFeedScreenContent()
        }
        composable(Screen.Comments.route) {
            commentsScreeContent()
        }

    }
}