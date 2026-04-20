package com.eliascardona.ecommerce.components.domain.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.components.shared.card.GenericCard
import com.eliascardona.ecommerce.infrastructure.data.ProductItemForCard

@Composable
fun ProductCard(
    product: ProductItemForCard,
    onProductItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .height(220.dp)
            .clickable(true, null, null, onProductItemClick)
    ) {
        _root_ide_package_.com.eliascardona.ecommerce.components.shared.card.GenericCard {
            ProductCardContent(product)
        }
    }
}

@Composable
fun ProductCardContent(
    product: ProductItemForCard
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            painter = painterResource(id = product.productImage),
            contentDescription = "Product image for ${product.productName}",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.productName,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1
        )

        Text(
            text = "$ 99.99",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun FeaturedProductCard(
    productItemForCard: ProductItemForCard,
    onProductItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(280.dp)
            .height(220.dp)
            .clickable(true, null, null, onProductItemClick)
    ) {
        GenericCard {
            FeaturedProductContent(product = productItemForCard)
        }
    }
}

@Composable
fun FeaturedProductContent(
    product: ProductItemForCard
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = product.productImage),
            contentDescription = "Product image for ${product.productName}",
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.productName,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1
        )

        Text(
            text = "$ 99.99",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}