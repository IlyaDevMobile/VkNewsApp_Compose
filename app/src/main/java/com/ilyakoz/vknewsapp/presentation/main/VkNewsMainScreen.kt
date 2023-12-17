@file:OptIn(ExperimentalMaterial3Api::class)

package com.ilyakoz.vknewsapp.presentation.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ilyakoz.vknewsapp.presentation.ViewModelFactory
import com.ilyakoz.vknewsapp.presentation.comments.CommentsScreen
import com.ilyakoz.vknewsapp.presentation.navigation.AppNavGraph
import com.ilyakoz.vknewsapp.presentation.navigation.rememberNavigationState
import com.ilyakoz.vknewsapp.presentation.news.NewsFeedScreen

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()




    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEach { item ->

                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(item.screen.route)
                            }
                        },
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = stringResource(item.titleResId))
                        },


                        )

                }
            }

        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            newFeedScreenContent = {

                NewsFeedScreen(
                    paddingValues = paddingValues,
                    onCommentClickListener = {
                        navigationState.navigationToComments(it)
                    }
                )

            },
            commentsScreeContent = { feedpost ->
                CommentsScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    feedPost = feedpost

                )
            },
            favouriteScreenContent = { Text(text = "Favourite") },
            profileScreenContent = { Text(text = "Profile") }
        )
    }
}



