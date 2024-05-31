package com.example.bookapp.storage

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface dao {
    @Insert
    fun Insert (book: Book)

    @Delete
    fun Delete (book: Book)

    @Update
    fun Update (book: Book)

    @Query("Select * from books")
    fun getAllBooks (): Flow<List<Book>>

    @Query("Select * from books where databaseId=:id")
    fun getBookById (id: String): Flow<Book>
}