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
