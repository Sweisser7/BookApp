package com.example.bookapp.widgets

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.twotone.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookapp.storage.Book
import com.example.bookapp.models.BookRepository
import com.example.bookapp.models.BookRepository.deleteBookById
import com.example.bookapp.models.BookRepository.getBooks
import com.example.bookapp.models.BookRepository.isValidISBN
import com.example.bookapp.viewmodels.BooksViewModel
import com.example.bookapp.viewmodels.EditViewModel

var isbn = mutableStateOf("")

@Composable
fun BookList (modifier: Modifier,
              book: List<Book>,
              booksviewModel: BooksViewModel){
    if (getBooks().isEmpty()) {
        Column (modifier = modifier) {
            Text(text = "Es wurde noch kein Buch angelegt.")
        }
    } else {
        LazyColumn(modifier = modifier) {
            items(book) { book ->
                BookRow(
                    book = book,
                    booksviewModel = booksviewModel,
                    onReadClick = {bookId ->

                    })
            }
        }
    }
}

@Composable
fun BookRow(
    modifier: Modifier = Modifier,
    book: Book,
    booksviewModel: BooksViewModel,
    onReadClick: (String) -> Unit = {}){
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable {
        },
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            BookHeader(book = book, isRead = book.isRead, booksviewModel = booksviewModel, onReadClick = { onReadClick(book.id)})

            BookDetails(modifier = modifier.padding(12.dp), book = book, booksViewModel = booksviewModel)
        }
    }
}

@Composable
fun BookHeader(
    isRead: Boolean = false,
    booksviewModel: BooksViewModel,
    onReadClick: () -> Unit = {},
    book: Book,
) {
    Row(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Title: " + book.title)
        ReadIcon(booksviewModel = booksviewModel, book)

    }
}

@Composable
fun BookDetails(modifier: Modifier, book: Book, booksViewModel: BooksViewModel) {
    var showDetails by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Author: " + book.author)
        Icon(modifier = Modifier
            .clickable {
                showDetails = !showDetails
            },
            imageVector =
            if (showDetails) Icons.Filled.KeyboardArrowDown
            else Icons.Default.KeyboardArrowUp, contentDescription = "show more")
    }


    AnimatedVisibility(
        visible = showDetails,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column (modifier = modifier) {
            Divider(modifier = Modifier.padding(3.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "Released: ${book.release}", style = MaterialTheme.typography.bodySmall)
                Icon(modifier = Modifier
                    .clickable {
                        booksViewModel.deleteBook(book)
                               },
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete book")
            }


            Text(text = "ISBN: ${book.isbn}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
@Composable
fun ReadIcon(
    booksviewModel: BooksViewModel,
    book: Book
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.TopEnd
    ){
        Icon(
            modifier = Modifier.clickable {
                var newBook = mutableStateOf(book)
                newBook.value.isRead = !book.isRead
                booksviewModel.updateReadBook(book)
                Log.i("BookWidget", "icon clicked")
            },
            tint = MaterialTheme.colorScheme.secondary,
            imageVector =
            if (book.isRead) {
                Icons.Filled.Done
            } else {
                Icons.TwoTone.AddCircle
            },

            contentDescription = "Add to read books")
    }
}

@Composable
fun AddBook(modifier: Modifier, editViewModel: EditViewModel) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var release by remember { mutableStateOf("") }
    var isRead by remember { mutableStateOf(false) }
    var id by remember { mutableStateOf(System.currentTimeMillis().toString()) }
    var errorMessageTitle by remember { mutableStateOf<String?>(null) }
    var errorMessageAuthor by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        SimpleTopAppBar (title = "Add/configure a book")
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = title,
            onValueChange = { newTitle ->
                            title = newTitle
                errorMessageTitle = if (newTitle.isNotEmpty()) {
                    null
                } else
                    "Invalid Title"
            },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth(),
            isError = errorMessageTitle != null
        )
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = author,
            onValueChange = { newAuthor ->
                author = newAuthor
                errorMessageAuthor = if (newAuthor.isNotEmpty()) {
                    null
                } else
                    "Invalid Author" },
            label = { Text("Author") },
            isError = errorMessageAuthor != null,
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = release,
            onValueChange = { release = it },
            label = { Text(text = "Release (YYYYMMDD)") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        ISBNTextField()
        Spacer(modifier = Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isRead,
                onCheckedChange = { isRead = it }
            )
            Text(text = "Is Read")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                Log.d("isbn", isbn.value)
                if (isValidISBN(isbn.value) && title.isNotEmpty() && author.isNotEmpty()) {
                    val book = Book(
                        id = id,
                        title = title,
                        author = author,
                        release = release.toInt(),
                        isbn = isbn.value,
                        isRead = isRead
                    )
                    editViewModel.addNewBook(book)
                    // Setze die Eingabefelder nach dem Hinzufügen zurück
                    title = ""
                    author = ""
                    release = ""
                    isbn.value = ""
                    isRead = false
                    id = System.currentTimeMillis().toString()
                }

            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Book")
        }
        if (errorMessageTitle != null) {
            Text(
                text = errorMessageTitle!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        if (errorMessageAuthor != null) {
            Text(
                text = errorMessageAuthor!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun ISBNTextField() {
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isISBNValid by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = isbn.value,
            onValueChange = { newIsbn ->
                isbn.value = newIsbn
                isISBNValid = isValidISBN(newIsbn)
                errorMessage = if (isValidISBN(newIsbn)) {
                    null
                } else {
                    "Invalid ISBN number"
                }
            },
            label = { Text("ISBN") },
            isError = errorMessage != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}