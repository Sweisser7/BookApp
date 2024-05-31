package com.example.bookapp.screens

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bookapp.storage.Book
import com.example.bookapp.viewmodels.EditViewModel
import com.example.bookapp.widgets.AddBook
import com.example.bookapp.widgets.SimpleBottomAppBar


@Composable
fun SettingScreen(
    navController: NavController,
    id: String?,
    editViewModel: EditViewModel
) {
    var newId = if (id != null)id.toLong() else 0.toLong()
    var book = editViewModel.getBookById(newId)
    Log.d("titleSettingScreen", book.title)
    Scaffold (

        bottomBar = {
            SimpleBottomAppBar(navController = navController)
        }
    ){ innerPadding ->
        AddBook(
            modifier = Modifier.padding(innerPadding),
            editViewModel = editViewModel,
            book = book
        )    }
}
