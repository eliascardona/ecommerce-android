package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.R
import com.eliascardona.ecommerce.infrastructure.items_management.ProductDetailsManager
import com.eliascardona.ecommerce.infrastructure.items_management.ProductSelectionManager
import com.eliascardona.ecommerce.infrastructure.items_management.SelectedProduct

@Composable
fun ProductDetails(
    onNavigateBackward: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ProductImageHeader(onNavigateBackward = onNavigateBackward)
        ProductDetailsBody()
    }
}

@Composable
fun ProductDetailsBody() {
    val productDetails = ProductDetailsManager.getProductDetails()

    val addToCart = {
        val selectedProduct = SelectedProduct(
            productId = productDetails?.productId ?: "",
            name = productDetails?.name ?: "",
            unitPrice = productDetails?.unitPrice ?: 65.0,
            quantity = 1,
            imageRes = productDetails?.imageRes ?: 1,
            shippingCost = productDetails?.shippingCost ?: 10.0
        )

        ProductSelectionManager.addProduct(selectedProduct)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Details",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        if (productDetails != null) {
            Text(
                text = productDetails.name,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("⭐ 4", color = Color(0xFFFFC107))
                Spacer(modifier = Modifier.width(8.dp))
                Text("(28 reviews)", color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(12.dp))

            AddToCartButton(
                productDetails = productDetails,
                onAddToCart = addToCart
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ProductImageHeader(
    onNavigateBackward: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.shoe1),
            contentDescription = "Product main image",
            modifier = Modifier.fillMaxSize()
        )

        // Top actions
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            CircleIconButton(
                Icons.AutoMirrored.Filled.ArrowBack,
                modifier = Modifier.clickable(
                    true,
                    null,
                    null,
                    onNavigateBackward
                )
            )

            Row {
                CircleIconButton(Icons.Default.FavoriteBorder,)
                Spacer(modifier = Modifier.width(8.dp))
                CircleIconButton(Icons.Default.Share,)
            }
        }
    }
}

@Composable
fun CircleIconButton(icon: ImageVector, modifier: Modifier = Modifier) {
    Surface(
        shape = CircleShape,
        color = Color.White,
        modifier = Modifier.size(48.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Icon"
            )
        }
    }
}

@Composable
fun AddToCartButton(
    productDetails: SelectedProduct,
    onAddToCart: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {
            Text(
                text = "$${productDetails.unitPrice}",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Button(
            onClick = onAddToCart,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .height(56.dp)
                .width(180.dp)
        ) {
            Text("Add to cart")
        }
    }
}