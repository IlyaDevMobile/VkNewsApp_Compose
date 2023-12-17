package com.ilyakoz.vknewsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ilyakoz.vknewsapp.domain.entity.FeedPost

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newFeedScreenContent: @Composable () -> Unit,
    favouriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    commentsScreeContent: @Composable (FeedPost) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        homeScreenNavGraph(
            newFeedScreenContent = newFeedScreenContent,
            commentsScreeContent = commentsScreeContent
        )
        composable(Screen.Favourite.route) {
            favouriteScreenContent()
        }
        composable(Screen.Profile.route) {
            profileScreenContent()
        }
    }
}