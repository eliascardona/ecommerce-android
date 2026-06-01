package com.eliascardona.ecommerce.domain.shopping_cart

import androidx.datastore.preferences.core.stringPreferencesKey

object ShoppingCartKeys {

    val CART_SELECTION =
        stringPreferencesKey(
            "cart_selection"
        )
}