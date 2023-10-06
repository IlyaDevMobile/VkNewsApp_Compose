@file:OptIn(ExperimentalMaterial3Api::class)

package com.ilyakoz.vknewsapp.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ilyakoz.vknewsapp.MainViewModel
import com.ilyakoz.vknewsapp.NavigationItem
import com.ilyakoz.vknewsapp.domain.FeedPost
import com.ilyakoz.vknewsapp.ui.PostCard

@Composable
fun MainScreen(viewModel: MainViewModel) {


    Scaffold(
        bottomBar = {
            NavigationBar {
                val selectedPositionItems = rememberSaveable {
                    mutableStateOf(0)
                }


                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedPositionItems.value == index,
                        onClick = { selectedPositionItems.value = index },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(item.titleResId))
                        },


                        )

                }
            }

        }
    ) {
        val feedPost = viewModel.feedPost.observeAsState(FeedPost())



        Column(Modifier.padding(paddingValues = it)) {
            PostCard(
                modifeir = Modifier.padding(8.dp),
                feedPost = feedPost.value,
                onViewsClickListener = viewModel::updateCount,
                onShareClickListener = viewModel::updateCount,
                onCommentsClickListener = viewModel::updateCount,
                onLikeClickListener = viewModel::updateCount,
            )
        }


    }
}


