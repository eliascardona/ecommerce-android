package com.eliascardona.ecommerce.domain.shopping_cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eliascardona.ecommerce.infrastructure.items_management.ProductSelectionManager
import kotlinx.coroutines.launch

class ShoppingCartPersistenceViewModel(
    private val repository: ShoppingCartRepository
) : ViewModel() {

    fun persistCurrentSelection() {

        viewModelScope.launch {

            repository.persistCart(
                ProductSelectionManager.snapshot()
            )
        }
    }

    fun restoreSelection() {

        viewModelScope.launch {

            repository
                .persistedCartFlow
                .collect { persistedProducts ->

                    ProductSelectionManager
                        .restoreSelection(
                            persistedProducts
                        )
                }
        }
    }

    fun clearPersistedCart() {

        viewModelScope.launch {

            repository.clearPersistedCart()
        }
    }
}