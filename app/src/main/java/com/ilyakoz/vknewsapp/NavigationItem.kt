package com.ilyakoz.vknewsapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val titleResId: Int,
    val icon: ImageVector
) {
    object Home : NavigationItem(
        titleResId = R.string.navigation_item_main,
        icon = Icons.Filled.Home
    )

    object Favourite : NavigationItem(
        titleResId = R.string.navigation_item_favourite,
        icon = Icons.Filled.Favorite
    )

    object Profile : NavigationItem(
        titleResId = R.string.navigation_item_profile,
        icon = Icons.Filled.AccountCircle
    )
}
