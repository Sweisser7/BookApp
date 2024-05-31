package com.example.bookapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookapp.screens.BookScreen
import com.example.bookapp.screens.SettingScreen
import com.example.bookapp.viewmodels.BooksViewModel
import com.example.bookapp.viewmodels.EditViewModel
import com.example.bookapp.viewmodels.Injector


@Composable
fun Navigation() {
    val navController = rememberNavController()

    val booksViewModel: BooksViewModel = viewModel(factory = Injector.provideModelFactory(context = LocalContext.current))
    val editViewModel: EditViewModel = viewModel(factory = Injector.provideModelFactory(context = LocalContext.current))

    NavHost(navController = navController,
        startDestination = Screen.BookScreen.route) {
        composable(route = Screen.BookScreen.route){
            BookScreen(navController = navController, booksViewModel = booksViewModel)
        }


        composable(route = Screen.SettingScreen.route + "/{bookId}",
            arguments = listOf(navArgument(name = "bookId") {type = NavType.StringType})
        ){ backStackEntry ->
            SettingScreen(navController = navController, editViewModel = editViewModel, id = backStackEntry.arguments?.getString(DETAIL_ARGUMENT_KEY))
        }
    }
}