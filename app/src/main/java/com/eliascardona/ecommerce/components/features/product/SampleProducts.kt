package com.eliascardona.ecommerce.components.features.product

import com.eliascardona.ecommerce.R
import com.eliascardona.ecommerce.infrastructure.data.ProductItemForCard

val sampleProducts = listOf(

    ProductItemForCard(
        productId = "laptop_pro_15",
        productName = "Laptop Pro 15",
        productPrice = 1599.99,
        productImage = R.drawable.shoe1
    ),

    ProductItemForCard(
        productId = "wireless_headphones",
        productName = "Wireless Headphones",
        productPrice = 199.99,
        productImage = R.drawable.shoe2
    ),

    ProductItemForCard(
        productId = "smartphone_x",
        productName = "Smartphone X",
        productPrice = 899.99,
        productImage = R.drawable.shoe3
    )
)