package com.eliascardona.ecommerce.components.features.product

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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.components.shared.card.GenericCard
import com.eliascardona.ecommerce.infrastructure.data.ProductEntity

@Composable
fun ProductCard(
    product: ProductEntity,
    onProductItemClick: () -> Unit,
    onAddItemToShoppingCart: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .height(220.dp)
            .clickable {
                onProductItemClick()
            }
    ) {
        GenericCard {
            ProductCardContent(
                product = product,
                onAddItemToShoppingCart = onAddItemToShoppingCart
            )
        }
    }
}

@Composable
fun ProductCardContent(
    product: ProductEntity,
    onAddItemToShoppingCart: () -> Unit
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
            contentDescription = product.name,
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.name,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1
        )

        Text(
            text = "$ ${product.price}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onAddItemToShoppingCart
            ) {
                Text("Add to cart")
            }
        }
    }
}

@Composable
fun FeaturedProductCard(
    product: ProductEntity,
    onProductItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(280.dp)
            .height(220.dp)
            .clickable(true, null, null, onProductItemClick)
    ) {
        GenericCard {
            FeaturedProductContent(product = product)
        }
    }
}

@Composable
fun FeaturedProductContent(
    product: ProductEntity
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = product.productImage),
            contentDescription = "Product image for ${product.name}",
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.name,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1
        )

        Text(
            text = "$${product.price}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}