package com.example.bookapp.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book (
    @PrimaryKey(autoGenerate = true)
    val databaseId: Long=0,
    var id: String,
    var title: String,
    var author: String,
    var release: Int,
    var isbn: String,
    var isRead: Boolean
)