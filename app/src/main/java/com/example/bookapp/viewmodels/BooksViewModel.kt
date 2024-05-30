package com.example.bookapp.viewmodels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.bookapp.models.Book
import com.example.bookapp.models.BookRepository


class BooksViewModel : ViewModel() {
    private val _books = BookRepository.getBooks().toMutableStateList()
    val books: List<Book>
        get() = _books

    fun toggleReadBook(bookId: String) = _books.find { it.id == bookId }?.let { book ->
        book.isRead = !book.isRead
    }



}