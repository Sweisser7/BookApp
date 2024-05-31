package com.example.bookapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bookapp.widgets.BookList
import com.example.bookapp.viewmodels.BooksViewModel
import com.example.bookapp.widgets.SimpleBottomAppBar
import com.example.bookapp.widgets.SimpleTopAppBar

@Composable
fun BookScreen(
    navController: NavController,
    booksViewModel: BooksViewModel
) {
    Scaffold (
        topBar = {
            SimpleTopAppBar (title = "Book App")
        },
        bottomBar = {
            SimpleBottomAppBar(navController = navController)
        }
    ){ innerPadding ->
        BookList(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            book = booksViewModel.allBooks.collectAsState().value,
            booksviewModel = booksViewModel
        )
    }
}