package com.eliascardona.ecommerce.infrastructure.data

data class ProductItemForCard(
    val productId: String,
    val productName: String,
    val productImage: Int,
    val productPrice: Double
)