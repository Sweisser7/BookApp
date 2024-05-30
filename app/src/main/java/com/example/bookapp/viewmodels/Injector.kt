package com.example.bookapp.viewmodels

import android.content.Context
import com.example.bookapp.storage.database
import com.example.bookapp.storage.repository

object Injector {
    private fun getRepository(context: Context): repository {
        return repository.returnInstance(database.getDatabase(context.applicationContext).dao())
    }

    fun provideModelFactory(context: Context): Factory {
        val repository = getRepository(context)
        return Factory(repository = repository)
    }
}