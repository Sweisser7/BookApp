package com.example.bookapp.storage

import androidx.room.Insert
import kotlinx.coroutines.flow.Flow

class repository (private val dao: dao) {
    companion object {
        @Volatile
        private var instance:repository?=null

        fun returnInstance (dao: dao) = instance ?: synchronized(this) {
            instance ?: repository(dao).also { instance=it }
        }

    }

    fun returnInsert (book: Book) = dao.Insert(book)
    fun returnUpdate (book: Book) = dao.Update(book)
    fun returnDelete (book: Book) = dao.Delete(book)
    fun returnAllBooks (): Flow<List<Book>> = dao.getAllBooks()


}