package com.eliascardona.ecommerce.components.features.product

import com.eliascardona.ecommerce.R
import com.eliascardona.ecommerce.infrastructure.data.ProductEntity

val sampleProducts = listOf(

    ProductEntity(
        "KrleuJ8pqY7vfCw6V5iZ",
        "Sport shoe",
        R.drawable.shoe1,
        30.0,
        3.0
    ),

    ProductEntity(
        "hbp4Df0FssXdFoS25EJX",
        "Running shoe",
        R.drawable.shoe2,
        40.0,
        shippingCost = 3.0
    ),
)