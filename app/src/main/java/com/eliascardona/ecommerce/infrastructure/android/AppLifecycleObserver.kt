package com.eliascardona.ecommerce.infrastructure.lifecycle

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.eliascardona.ecommerce.domain.shopping_cart.ShoppingCartRepository
import com.eliascardona.ecommerce.infrastructure.items_management.ProductSelectionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppLifecycleObserver(
    private val cartRepository: ShoppingCartRepository
) : DefaultLifecycleObserver {

    override fun onStop(owner: LifecycleOwner) {

        CoroutineScope(Dispatchers.IO).launch {

            cartRepository.persistCart(
                ProductSelectionManager.snapshot()
            )
        }
    }
}