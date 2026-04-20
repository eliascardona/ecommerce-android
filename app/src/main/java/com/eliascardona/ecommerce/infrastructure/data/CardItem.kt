package com.eliascardona.ecommerce.infrastructure.data

data class CartItem(
    val productName: String,
    val size: String,
    val price: String,
    val quantity: Int,
    val image: Int
)