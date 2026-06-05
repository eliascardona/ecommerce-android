package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.components.features.product.FeaturedProductCard
import com.eliascardona.ecommerce.infrastructure.data.ProductManager

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
        Spacer(modifier = Modifier.height(16.dp))

        val products = ProductManager.snapshot()
        val featuredProduct = products.firstOrNull()

        if (featuredProduct != null) {
            FeaturedProductCard(
                product = featuredProduct,
                onProductItemClick = onProductItemClick
            )
        }
    }
}