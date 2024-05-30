package com.example.bookapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookapp.storage.repository

class Factory (private val repository: repository):ViewModelProvider.Factory{
    @Override
    override fun <T:ViewModel> create(model: Class<T>):T=when (model) {
        BooksViewModel::class.java -> BooksViewModel(repository = repository)
        EditViewModel::class.java -> EditViewModel(repository = repository)
        else -> throw IllegalArgumentException("Oh oh!")
    } as T
}