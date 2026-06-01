package com.eliascardona.ecommerce.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.components.features.shopping_cart.CartPreviewDialog
import com.eliascardona.ecommerce.infrastructure.items_management.ProductSelectionManager

@Composable
fun ShoppingHeader(
    title: String,
    onNavigateToCart: () -> Unit,
    onNavigateToCheckout: () -> Unit
) {
    val selection by ProductSelectionManager
        .selection
        .collectAsState()

    val selectedProducts =
        selection.values.toList()

    var showCartDialog by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )

        IconButton(
            onClick = { showCartDialog = true }
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Shopping cart",
                modifier = Modifier.size(28.dp)
            )
        }
    }

    if (showCartDialog) {
        CartPreviewDialog(
            products = selectedProducts,
            onDismiss = {
                showCartDialog = false
            },
            onNavigateToCart = {
                showCartDialog = false
                onNavigateToCart()
            },
            onNavigateToCheckout = {
                showCartDialog = false
                onNavigateToCheckout()
            }
        )
    }
}