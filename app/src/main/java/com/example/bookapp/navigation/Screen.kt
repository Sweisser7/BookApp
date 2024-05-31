package com.example.bookapp.navigation

const val DETAIL_ARGUMENT_KEY = "bookId"
sealed class Screen(val route: String) {
    object BookScreen : Screen("books")
    object SettingScreen : Screen("settings")
}