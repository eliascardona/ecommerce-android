package com.eliascardona.ecommerce.domain.shopping_cart

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.eliascardona.ecommerce.infrastructure.items_management.SelectedProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShoppingCartRepository(
    private val context: Context
) {

    val persistedCartFlow:
            Flow<List<SelectedProduct>> =

        context.dataStore.data.map { prefs ->

            val json =
                prefs[
                    ShoppingCartKeys.CART_SELECTION
                ] ?: ""

            SelectedProductSerializer
                .deserialize(json)
        }

    suspend fun persistCart(
        products: List<SelectedProduct>
    ) {

        val serialized =
            SelectedProductSerializer
                .serialize(products)

        context.dataStore.edit { prefs ->

            prefs[
                ShoppingCartKeys.CART_SELECTION
            ] = serialized
        }
    }

    suspend fun clearPersistedCart() {

        context.dataStore.edit { prefs ->

            prefs.remove(
                ShoppingCartKeys.CART_SELECTION
            )
        }
    }
}