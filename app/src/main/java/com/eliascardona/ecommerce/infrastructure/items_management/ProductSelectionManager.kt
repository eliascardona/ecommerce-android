package com.eliascardona.ecommerce.infrastructure.items_management

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object ProductSelectionManager {

    private val _selection =
        MutableStateFlow<Map<String, SelectedProduct>>(emptyMap())

    val selection: StateFlow<Map<String, SelectedProduct>> =
        _selection.asStateFlow()

    fun addProduct(product: SelectedProduct) {

        val current = _selection.value.toMutableMap()

        val existing = current[product.productId]

        current[product.productId] =
            if (existing != null) {
                existing.copy(
                    quantity = existing.quantity + 1
                )
            } else {
                product
            }

        _selection.value = current
    }

    fun removeProduct(productId: String) {

        val current = _selection.value.toMutableMap()

        val existing = current[productId]
            ?: return

        val newQuantity = existing.quantity - 1

        if (newQuantity <= 0) {
            current.remove(productId)
        } else {
            current[productId] =
                existing.copy(quantity = newQuantity)
        }

        _selection.value = current
    }

    fun clearSelection() {
        _selection.value = emptyMap()
    }

    fun restoreSelection(
        products: List<SelectedProduct>
    ) {

        _selection.value =
            products.associateBy {
                it.productId
            }
    }

    fun snapshot(): List<SelectedProduct> {
        return _selection.value.values.toList()
    }
}