package com.eliascardona.ecommerce.infrastructure.data

import kotlinx.coroutines.flow.MutableStateFlow

object ProductManager {
    private val _products = MutableStateFlow<List<ProductEntity>>(emptyList())

    fun loadProducts(productsFromDB: List<ProductEntity>) {
        _products.value = productsFromDB
    }

    fun snapshot(): List<ProductEntity> {
        return _products.value.toList()
    }
}