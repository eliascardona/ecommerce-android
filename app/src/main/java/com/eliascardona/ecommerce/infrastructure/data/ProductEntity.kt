package com.eliascardona.ecommerce.infrastructure.data

data class ProductEntity(
    val productId: String,
    val productName: String,
    val productImage: Int,
    val productPrice: Double,
    val shippingCost: Double = 0.0
)