package com.eliascardona.ecommerce.infrastructure.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object OrderManager {
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders.asStateFlow()

    fun placeOrder(items: List<OrderItem>, total: String) {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val newOrder = Order(
            id = "ORD-${UUID.randomUUID().toString().take(6).uppercase()}",
            date = dateFormat.format(Date()),
            status = "Processing",
            items = items,
            total = total
        )
        _orders.value = listOf(newOrder) + _orders.value
    }
}
