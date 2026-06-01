package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.R
import com.eliascardona.ecommerce.components.features.product.FeaturedProductCard
import com.eliascardona.ecommerce.infrastructure.data.ProductItemForCard

val featuredLaptop = ProductItemForCard(
    "123",
    "Laptop Pro 15",
    R.drawable.shoe1,
    12.00
)

@Composable
fun Featured(
    onProductItemClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SectionHeader("Sample products carousel")

        /*
            Featured product goes here
        */
        Spacer(modifier = Modifier.height(16.dp))
        FeaturedProductCard(
            productItemForCard = featuredLaptop,
            onProductItemClick = onProductItemClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("Featured product")

        /*
            Featured product goes here
        */
        Spacer(modifier = Modifier.height(16.dp))
        FeaturedProductCard(
            productItemForCard = featuredLaptop,
            onProductItemClick = onProductItemClick
        )

        SectionHeader("Featured product")

        /*
            Featured product goes here
        */
        Spacer(modifier = Modifier.height(16.dp))
        FeaturedProductCard(
            productItemForCard = featuredLaptop,
            onProductItemClick = onProductItemClick
        )
        /* End of custom components */
    }
}