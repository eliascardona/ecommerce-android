package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.R
import com.eliascardona.ecommerce.components.features.product.FeaturedProductCard
import com.eliascardona.ecommerce.infrastructure.data.ProductEntity

val featuredProduct = ProductEntity(
    "feat_shoe",
    "Featured shoe",
    R.drawable.shoe1,
    65.0
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
        SectionHeader("Featured product")

        /*
            Featured product goes here
        */
        Spacer(modifier = Modifier.height(16.dp))
        FeaturedProductCard(
            product = featuredProduct,
            onProductItemClick = onProductItemClick
        )
    }
}