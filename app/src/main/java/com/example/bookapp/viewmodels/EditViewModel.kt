package com.example.bookapp.viewmodels

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.storage.Book
import com.example.bookapp.storage.repository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EditViewModel(val repository: repository) : ViewModel(), BasicViewModel {

    fun addNewBook (book: Book) {
        repository.returnInsert(book)
    }
    fun editBook(book: Book) {
        repository.returnUpdate(book)
    }
    fun getBookById(id: Long): Book {
        var book: Book?=null
        viewModelScope.launch {
            repository.getBookById(id).collect {book = it}
        }
        if (book == null) book = Book(id = System.currentTimeMillis().toString(),title = "", author = "", release = 0, isbn = "", isRead = false)
        //Log.d("getBookById", book.title)
        return book as Book
    }
}