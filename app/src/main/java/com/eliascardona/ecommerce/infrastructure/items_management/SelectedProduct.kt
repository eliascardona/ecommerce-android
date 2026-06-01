package com.eliascardona.ecommerce.infrastructure.items_management

data class SelectedProduct(
    val productId: String,
    val name: String,
    val unitPrice: Double,
    val quantity: Int,
    val imageRes: Int,
    val shippingCost: Double = 0.0
)