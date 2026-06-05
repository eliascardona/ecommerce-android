package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.components.features.product.ProductCardCarousel
import com.eliascardona.ecommerce.infrastructure.data.ProductManager
import com.eliascardona.ecommerce.infrastructure.items_management.ProductDetailsManager

@Composable
fun HomeScreen(
    onNavigateToProductDetails: () -> Unit
) {
    val products = ProductManager.snapshot()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SectionHeader("Latest Products")

        Spacer(modifier = Modifier.height(16.dp))

        if (products.isEmpty()) {
            Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            ProductCardCarousel(
                products = products,
                onNavigateToProductDetails = { product ->
                    ProductDetailsManager.setProductDetails(product)
                    onNavigateToProductDetails()
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("Featured Products")

        Spacer(modifier = Modifier.height(16.dp))

        if (products.isEmpty()) {
            Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            // For now, using the same list, but could be filtered for featured products
            ProductCardCarousel(
                products = products.reversed(),
                onNavigateToProductDetails = { product ->
                    ProductDetailsManager.setProductDetails(product)
                    onNavigateToProductDetails()
                }
            )
        }
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
