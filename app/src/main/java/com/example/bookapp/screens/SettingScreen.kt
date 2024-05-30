package com.example.bookapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bookapp.viewmodels.BooksViewModel
import com.example.bookapp.viewmodels.EditViewModel
import com.example.bookapp.widgets.AddBook
import com.example.bookapp.widgets.BookList
import com.example.bookapp.widgets.SimpleBottomAppBar
import com.example.bookapp.widgets.SimpleTopAppBar


@Composable
fun SettingScreen(
    navController: NavController,
    editViewModel: EditViewModel
) {
    Scaffold (

        bottomBar = {
            SimpleBottomAppBar(navController = navController)
        }
    ){ innerPadding ->
        AddBook(
            modifier = Modifier.padding(innerPadding),
            editViewModel = editViewModel
        )    }
}
