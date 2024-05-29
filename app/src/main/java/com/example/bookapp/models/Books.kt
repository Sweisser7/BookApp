package com.example.bookapp.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Book(
    val id: String,
    var title: String,
    var author: String,
    var release: Int,
    var isbn: String,
    val initialIsRead: Boolean = false
) {
    var isRead by mutableStateOf(initialIsRead)
}

object BookRepository {
    private val books: MutableList<Book>  = mutableListOf(
    )

    fun getBooks(): List<Book> {
        return listOf(*books.toTypedArray())
    }

    fun addBook(book: Book) {
        books.add(book)
    }

    fun deleteBookById(id: String): Boolean {
        return books.removeIf { it.id == id }
    }

    fun editBook(id: String, newTitle: String, newAuthor: String, newRelease: Int, newIsbn: String): Boolean {
        val book = books.find { it.id == id }
        return if (book != null) {
            book.title = newTitle
            book.author = newAuthor
            book.release = newRelease
            book.isbn = newIsbn
            true
        } else {
            false
        }
    }
}

