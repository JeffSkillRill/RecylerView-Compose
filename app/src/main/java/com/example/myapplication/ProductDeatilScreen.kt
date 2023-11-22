package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductDetailScreen(
    productName: String,
    productDescription: String,
    imageResId: Int,
    price: Double,
    discount: Int,
    rating: Float

) {
    Box(
        modifier = Modifier

            .padding(16.dp)
            .background(Color.LightGray) // Change the color as needed
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
    )
    {
        Column(
            modifier = Modifier

                .padding(16.dp)
        ) {
            // Display product image with like button
            ProductImageWithLikeButton(imageResId = imageResId)

            Spacer(modifier = Modifier.height(16.dp))

            // Display product details
            ProductDetails(
                productName = productName,
                productDescription = productDescription,
                price = price,
                discount = discount,
                rating = rating
            )
        }
    }
}

@Composable
fun ProductImageWithLikeButton(imageResId: Int) {
    Box {
        val image: Painter = painterResource(id = imageResId)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        LikeButton(modifier = Modifier.align(Alignment.TopEnd))
    }
}

@Composable
fun LikeButton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(16.dp)
            .size(48.dp)
    ) {
        // You can replace the icon with your own custom like icon
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Like",
            tint = Color.Red
        )
    }
}

@Composable
fun ProductDetails(
    productName: String,
    productDescription: String,
    price: Double,
    discount: Int,
    rating: Float

) {
    Column {
        Text(
            text = productName,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = productDescription,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = buildAnnotatedString {
                    append("$")
                    pushStyle(
                        SpanStyle(
                            color = Color.Blue, // Change color as needed
                            textDecoration = TextDecoration.LineThrough
                        )
                    )
                    append("$price")
                    pop()
                },
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "-$discount%",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Rating",
                tint = Color.Yellow,
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = "$rating",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray // Change color as needed
                ),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}
@Composable
fun ProductItem(product: Product) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Display product image with like button
            ProductImageWithLikeButton(imageResId = product.imageResId)

            Spacer(modifier = Modifier.height(16.dp))

            // Display product details
            ProductDetails(
                productName = product.productName,
                productDescription = product.productDescription,
                price = product.price,
                discount = product.discount,
                rating = product.rating
            )
        }
    }
}

@Composable
fun ProductList(products: List<Product>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(products) { product ->
            ProductItem(product = product)
            Divider() // Add a divider between items if needed
        }
    }
}

@Composable
fun ProductGrid(products: List<Product>, onItemClick: (Product) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        content = {
            items(
                count = (products.size + 1) / 2,
                itemContent = { rowIndex ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        val startIndex = rowIndex * 2
                        val endIndex = minOf(startIndex + 2, products.size)
                        for (index in startIndex until endIndex) {
                            ProductItem(
                                product = products[index],
                                //onItemClick = onItemClick
                            )
                        }
                    }
                }
            )
        }
    )
}

@Preview
@Composable
fun ProductDetailPreview() {
    val productName = "Product"
    val productDescription = "Product description"
    val imageResId = R.drawable.man_shirt
    val price = 49.99
    val discount = 25
    val rating = 4.5f


    ProductDetailScreen(
        productName = productName,
        productDescription = productDescription,
        imageResId = imageResId,
        price = price,
        discount = discount,
        rating = rating
    )
}

