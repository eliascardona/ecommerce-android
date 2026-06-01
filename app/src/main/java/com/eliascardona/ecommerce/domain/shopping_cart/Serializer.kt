package com.eliascardona.ecommerce.domain.shopping_cart

import com.eliascardona.ecommerce.infrastructure.items_management.SelectedProduct
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object SelectedProductSerializer {

    private val gson = Gson()

    fun serialize(
        products: List<SelectedProduct>
    ): String {

        return gson.toJson(products)
    }

    fun deserialize(
        json: String
    ): List<SelectedProduct> {

        if (json.isBlank()) {
            return emptyList()
        }

        val type =
            object : TypeToken<List<SelectedProduct>>() {}
                .type

        return gson.fromJson(json, type)
    }
}