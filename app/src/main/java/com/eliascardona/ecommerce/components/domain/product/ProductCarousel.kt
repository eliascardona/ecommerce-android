package com.eliascardona.ecommerce.components.domain.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.R
import com.eliascardona.ecommerce.infrastructure.data.ProductItemForCard

// SAMPLE DATA
val sampleProducts = listOf(
    ProductItemForCard("Laptop Pro 15", R.drawable.shoe1),
    ProductItemForCard("Wireless Headphones", R.drawable.shoe2),
    ProductItemForCard("Smartphone X", R.drawable.shoe3),
    ProductItemForCard("Gaming Mouse", R.drawable.shoe4),
    ProductItemForCard("Mechanical Keyboard", R.drawable.shoe5)
)

@Composable
fun ProductCardCarousel(
    onProductItemClick: () -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(sampleProducts) { product ->
            ProductCard(product, onProductItemClick)
        }
    }
}