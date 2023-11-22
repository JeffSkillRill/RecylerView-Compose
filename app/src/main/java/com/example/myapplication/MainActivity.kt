package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContent {

                MyProductGridScreen()
            }
        }
    }
}
@Composable
fun MyProductGridScreen() {
    val productList = listOf(
        // Populate this list with your product items
        Product(1, "Product 1", "Description 1", R.drawable.man_shirt, 49.99, 20, 4.5f),
        Product(2, "Product 2", "Description 2", R.drawable.man_shirt, 29.99, 15, 4.0f),
        Product(1, "Product 2", "Description 2", R.drawable.man_shirt, 29.99, 15, 4.0f),
        Product(2, "Product 2", "Description 2", R.drawable.man_shirt, 29.99, 15, 4.0f),
        Product(1, "Product 2", "Description 2", R.drawable.man_shirt, 29.99, 15, 4.0f),
        Product(2, "Product 2", "Description 2", R.drawable.man_shirt, 29.99, 15, 4.0f),
        // Add more product items as needed
    )

    ProductGrid(products = productList) { product ->
        // Handle item click here
    }
}
