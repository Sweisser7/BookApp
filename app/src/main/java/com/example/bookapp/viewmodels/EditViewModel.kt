package com.example.bookapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.storage.Book
import com.example.bookapp.storage.repository

class EditViewModel(val repository: repository) : ViewModel(), BasicViewModel {

    fun addNewBook (book: Book) {
        repository.returnInsert(book)
    }
}