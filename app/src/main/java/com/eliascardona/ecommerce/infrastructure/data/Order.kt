package com.eliascardona.ecommerce.infrastructure.data

data class Order(
    val id: String,
    val date: String,
    val status: String,
    val items: List<OrderItem>,
    val total: String
)