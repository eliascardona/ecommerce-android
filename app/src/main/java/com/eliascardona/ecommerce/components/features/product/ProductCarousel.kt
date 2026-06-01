package com.eliascardona.ecommerce.components.features.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.infrastructure.data.ProductItemForCard
import com.eliascardona.ecommerce.infrastructure.items_management.ProductSelectionManager
import com.eliascardona.ecommerce.infrastructure.items_management.SelectedProduct

@Composable
fun ProductCardCarousel(
    products: List<ProductItemForCard>,
    onNavigateToProductDetails: () -> Unit
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {

        items(products) { product ->

            ProductCard(
                product = product,
                onProductItemClick = onNavigateToProductDetails,
                onAddItemToShoppingCart = {
                    ProductSelectionManager.addProduct(

                        SelectedProduct(
                            productId = product.productId,
                            name = product.productName,
                            unitPrice = product.productPrice,
                            quantity = 1,
                            imageRes = product.productImage
                        )
                    )
                }
            )
        }
    }
}