package com.eliascardona.ecommerce.infrastructure.data

data class Order(
    val id: String = "",
    val date: String = "",
    val status: String = "",
    val items: List<OrderItem> = emptyList(),
    val total: String = "",
    val userId: String = "",
    val userName: String = "",
    val userEmail: String = "",
    val shippingAddress: String = ""
)
