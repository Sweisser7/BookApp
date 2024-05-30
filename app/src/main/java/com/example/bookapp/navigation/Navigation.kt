package com.example.bookapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookapp.screens.BookScreen
import com.example.bookapp.screens.SettingScreen
//import com.example.bookapp.screens.SettingScreen

import com.example.bookapp.viewmodels.BooksViewModel
import com.example.bookapp.viewmodels.EditViewModel
import com.example.bookapp.viewmodels.Factory
import com.example.bookapp.viewmodels.Injector


@Composable
fun Navigation() {
    val navController = rememberNavController() // create a NavController instance

    val booksViewModel: BooksViewModel = viewModel(factory = Injector.provideModelFactory(context = LocalContext.current))
    val editViewModel: EditViewModel = viewModel(factory = Injector.provideModelFactory(context = LocalContext.current))// create a MoviesViewModel instance to use in HomeScreen and WatchlistScreen

    NavHost(navController = navController, // pass the NavController to NavHost
        startDestination = Screen.BookScreen.route) {  // pass a start destination
        composable(route = Screen.BookScreen.route){   // route with name "homescreen" navigates to HomeScreen composable
            BookScreen(navController = navController, booksViewModel = booksViewModel)
        }


        composable(route = Screen.SettingScreen.route){
            SettingScreen(navController = navController, editViewModel = editViewModel)
        }
    }
}