package com.example.bookapp.viewmodels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.models.BookRepository
import com.example.bookapp.storage.Book
import com.example.bookapp.storage.repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class BooksViewModel(val repository: repository) : ViewModel(), BasicViewModel {

    private val mutableAllBooks = MutableStateFlow(listOf<Book>())

    val allBooks: StateFlow<List<Book>> = mutableAllBooks.asStateFlow()

    fun deleteBook (book: Book) {
        repository.returnDelete(book)
    }

    fun updateReadBook (book: Book) {
        repository.returnUpdate(book)
    }

    init {
        viewModelScope.launch { repository.returnAllBooks().distinctUntilChanged().collect() {
            books -> mutableAllBooks.value = books
        } }
    }


}