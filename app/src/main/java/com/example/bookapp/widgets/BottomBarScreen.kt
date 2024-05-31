package com.example.bookapp.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bookapp.navigation.Screen

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Books: BottomBarScreen(
        route = Screen.BookScreen.route,
        title = "Books",
        icon = Icons.Filled.Favorite
    )

    object Settings: BottomBarScreen(
        route = Screen.SettingScreen.route + "/0",
        title = "Settings",
        icon = Icons.Filled.Build
    )
}