package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.R
import com.eliascardona.ecommerce.components.domain.product.FeaturedProductCard
import com.eliascardona.ecommerce.components.domain.product.ProductCardCarousel
import com.eliascardona.ecommerce.infrastructure.data.ProductItemForCard

val laptop = ProductItemForCard("Laptop Pro 15", R.drawable.shoe1)

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
        Spacer(modifier = Modifier.height(16.dp))
        ProductCardCarousel(
            onProductItemClick = onProductItemClick
        )

        Spacer(modifier = Modifier.height(16.dp))
        SectionHeader("Featured product")
        Spacer(modifier = Modifier.height(16.dp))

        FeaturedProductCard(
            productItemForCard = laptop,
            onProductItemClick = onProductItemClick
        )
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
