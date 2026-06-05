package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.components.shared.card.GenericCard
import com.eliascardona.ecommerce.components.shared.SummaryRow
import com.eliascardona.ecommerce.infrastructure.items_management.ProductSelectionManager
import com.eliascardona.ecommerce.infrastructure.items_management.SelectedProduct
import java.util.Locale

@Composable
fun ShoppingCart(
    onNavigateToCheckout: () -> Unit
) {
    val selection by ProductSelectionManager.selection.collectAsState()
    val cartItems = selection.values.toList()

    val subtotal = cartItems.sumOf { it.unitPrice * it.quantity }
    val shipping = cartItems.sumOf { it.shippingCost * it.quantity }
    val tax = subtotal * 0.10
    val total = subtotal + shipping + tax

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Shopping Cart",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "${cartItems.sumOf { it.quantity }} items",
            color = Color.Gray,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.weight(1f)) {
            if (cartItems.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Your cart is empty", color = Color.Gray)
                }
            } else {
                ShoppingCartBody(
                    cartItems = cartItems,
                    onIncrease = { ProductSelectionManager.addProduct(it) },
                    onDecrease = { ProductSelectionManager.removeProduct(it.productId) },
                    onDelete = { ProductSelectionManager.deleteProduct(it.productId) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OrderSummaryCard(
            subtotal = subtotal,
            shipping = shipping,
            tax = tax,
            total = total,
            onNavigateToCheckout = onNavigateToCheckout,
            enabled = cartItems.isNotEmpty()
        )
    }
}

@Composable
fun ShoppingCartBody(
    cartItems: List<SelectedProduct>,
    onIncrease: (SelectedProduct) -> Unit,
    onDecrease: (SelectedProduct) -> Unit,
    onDelete: (SelectedProduct) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(cartItems, key = { it.productId }) { item ->
            GenericCard {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.name,
                        modifier = Modifier.size(80.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.Red,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable { onDelete(item) }
                            )
                        }

                        Text(
                            text = String.format(Locale.getDefault(), "$%.2f", item.unitPrice),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        QuantitySelector(
                            quantity = item.quantity,
                            onIncrease = { onIncrease(item) },
                            onDecrease = { onDecrease(item) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun QuantitySelector(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleButton(Icons.Default.Delete, onClick = onDecrease)

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = quantity.toString(),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(12.dp))

        CircleButton(Icons.Default.Add, onClick = onIncrease)
    }
}

@Composable
fun CircleButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    Surface(
        shape = CircleShape,
        modifier = Modifier
            .size(32.dp)
            .clickable { onClick() },
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = null
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun OrderSummaryCard(
    subtotal: Double,
    shipping: Double,
    tax: Double,
    total: Double,
    onNavigateToCheckout: () -> Unit,
    enabled: Boolean
) {
    GenericCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                "Order Summary",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            SummaryRow("Subtotal", String.format(Locale.getDefault(), "$%.2f", subtotal))
            SummaryRow("Shipping", String.format(Locale.getDefault(), "$%.2f", shipping))
            SummaryRow("Tax (10%)", String.format(Locale.getDefault(), "$%.2f", tax))

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Total", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                    Text(
                        String.format(Locale.getDefault(), "$%.2f", total),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onNavigateToCheckout,
                    enabled = enabled,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.height(48.dp)
                ) {
                    Text("Checkout")
                }
            }
        }
    }
}
