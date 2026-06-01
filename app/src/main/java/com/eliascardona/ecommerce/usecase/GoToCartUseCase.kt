package com.eliascardona.ecommerce.usecase

import com.eliascardona.ecommerce.domain.shopping_cart.ShoppingCartRepository
import com.eliascardona.ecommerce.infrastructure.items_management.ProductSelectionManager

class GoToCartUseCase(
    private val cartRepository: ShoppingCartRepository
) {

    suspend operator fun invoke() {

        val snapshot =
            ProductSelectionManager.snapshot()

        cartRepository.persistCart(snapshot)
    }
}