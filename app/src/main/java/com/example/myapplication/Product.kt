package com.example.myapplication

data class Product(
    val id: Int,
    val productName: String,
    val productDescription: String,
    val imageResId: Int,
    val price: Double,
    val discount: Int,
    val rating: Float
)
