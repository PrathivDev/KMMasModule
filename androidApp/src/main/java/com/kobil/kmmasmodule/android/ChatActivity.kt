package com.kobil.kmmasmodule.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kobil.kmmasmodule.Greeting

class ChatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingView()
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String? = null) {
    Text(text ?: Greeting().greet())
}

@Composable
fun NetworkImage(modifier: Modifier = Modifier, imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Sample Image",
        modifier = modifier.size(128.dp),
        contentScale = ContentScale.Crop
    )
}

