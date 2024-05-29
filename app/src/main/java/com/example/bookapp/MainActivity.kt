package com.example.bookapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bookapp.navigation.Navigation
import com.example.bookapp.ui.theme.BookAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookAppTheme {
                Navigation()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart called.")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume called.")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause called.")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop called.")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onRestart called.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy called.")
    }
}

/*@Composable
fun BookList (modifier: Modifier, book: List<Book> = getBooks()){
    if (getBooks().isEmpty()) {
        Column (modifier = modifier) {
            Text(text = "Es wurde noch kein Buch angelegt.")
        }
    } else {
    LazyColumn(modifier = modifier) {
        items(book) { book ->
            BookRow(book)
            }
        }
    }
}

@Composable
fun BookRow(book: Book){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            BookDetails(modifier = Modifier.padding(12.dp), book = book)
        }
    }
}
@Composable
fun BookDetails(modifier: Modifier, book: Book) {
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
        Text(text = "Title: " + book.title)

        Icon(modifier = Modifier
            .clickable {
                showDetails = !showDetails
            },
            imageVector =
            if (showDetails) Icons.Filled.KeyboardArrowDown
            else Icons.Default.KeyboardArrowUp, contentDescription = "show more")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = "Author: " + book.author)
    }


    AnimatedVisibility(
        visible = showDetails,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column (modifier = modifier) {
            Divider(modifier = Modifier.padding(3.dp))

            Text(text = "Released: ${book.release}", style = MaterialTheme.typography.bodySmall)
            Text(text = "ISBN: ${book.isbn}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
fun DefaultPreview(){
    BookAppTheme {
            BookList(modifier = Modifier, book = getBooks())
    }*/
//}