package com.eliascardona.ecommerce.infrastructure.data

data class CartItem(
    val productId: String,
    val name: String,
    val unitPrice: Double,
    val quantity: Int
)