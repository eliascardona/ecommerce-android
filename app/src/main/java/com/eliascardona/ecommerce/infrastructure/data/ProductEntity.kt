package com.eliascardona.ecommerce.infrastructure.data

data class ProductEntity(
    val productId: String = "",
    val name: String = "",
    val productImage: Int = 0,
    val price: Double = 0.0,
    val shippingCost: Double = 0.0
)
