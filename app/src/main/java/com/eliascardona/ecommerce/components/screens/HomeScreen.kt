package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.components.features.product.ProductCardCarousel
import com.eliascardona.ecommerce.components.features.product.sampleProducts
import com.eliascardona.ecommerce.infrastructure.items_management.ProductDetailsManager

@Composable
fun HomeScreen(
    onNavigateToProductDetails: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SectionHeader("Sample products carousel")

        /*
            Product carousel goes here
        */
        Spacer(modifier = Modifier.height(16.dp))

        ProductCardCarousel(
            products = sampleProducts,
            onNavigateToProductDetails = { product ->
                ProductDetailsManager.setProductDetails(product)
                onNavigateToProductDetails()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("Featured product")

        /*
            Product carousel goes here
        */
        Spacer(modifier = Modifier.height(16.dp))

        ProductCardCarousel(
            products = sampleProducts,
            onNavigateToProductDetails = { product ->
                ProductDetailsManager.setProductDetails(product)
                onNavigateToProductDetails()
            }
        )
        /* End of custom components */
    }
}

@Composable
fun SectionHeader(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge
        )
    }
}
