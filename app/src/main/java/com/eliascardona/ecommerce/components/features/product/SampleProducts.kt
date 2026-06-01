package com.eliascardona.ecommerce.components.features.product

import com.eliascardona.ecommerce.R
import com.eliascardona.ecommerce.infrastructure.data.ProductEntity

val sampleProducts = listOf(

    ProductEntity(
        productId = "shoe1",
        productName = "Sport shoe",
        productPrice = 30.0,
        productImage = R.drawable.shoe1,
        shippingCost = 3.0
    ),

    ProductEntity(
        productId = "shoe2",
        productName = "Running shoe",
        productPrice = 40.0,
        productImage = R.drawable.shoe2,
        shippingCost = 3.0
    ),

    ProductEntity(
        productId = "shoe3",
        productName = "Casual shoe",
        productPrice = 20.0,
        productImage = R.drawable.shoe3,
        shippingCost = 3.0
    )
)