package com.eliascardona.ecommerce.infrastructure.items_management

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object ProductDetailsManager {
    private val _selectedProduct = MutableStateFlow<SelectedProduct?>(null)

    fun setProductDetails(product: SelectedProduct) {
        _selectedProduct.value = product
    }

    fun getProductDetails(): SelectedProduct? {
        return _selectedProduct.value
    }
    
    fun clearSelection() {
        _selectedProduct.value = null
    }
}
