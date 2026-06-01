package com.eliascardona.ecommerce.usecase

import com.eliascardona.ecommerce.domain.shopping_cart.ShoppingCartRepository
import com.eliascardona.ecommerce.infrastructure.items_management.ProductSelectionManager

class LogoutUseCase(
    private val cartRepository: ShoppingCartRepository
) {

    suspend operator fun invoke() {

        cartRepository.persistCart(
            ProductSelectionManager.snapshot()
        )

        ProductSelectionManager.clearSelection()
    }
}