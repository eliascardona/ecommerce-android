package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.components.layout.generic.GenericScreenHeader
import com.eliascardona.ecommerce.components.shared.card.GenericCard
import com.eliascardona.ecommerce.infrastructure.data.OrderItem
import com.eliascardona.ecommerce.infrastructure.data.OrderManager
import com.eliascardona.ecommerce.infrastructure.items_management.ProductSelectionManager
import java.util.Locale

@Composable
fun CheckoutScreen(
    onPlaceOrderNavigate: () -> Unit,
    onNavigateBackward: () -> Unit
) {
    val cartItems = ProductSelectionManager.snapshot()
    val scrollState = rememberScrollState()

    val subtotal = cartItems.sumOf { it.unitPrice * it.quantity }
    val shipping = cartItems.sumOf { it.shippingCost * it.quantity }
    val tax = subtotal * 0.10
    val total = subtotal + shipping + tax

    val triggerPlaceOrder = {
        val orderItems = cartItems.map {
            OrderItem(
                name = it.name,
                quantity = it.quantity,
                price = String.format(Locale.getDefault(), "$%.2f", it.unitPrice)
            )
        }
        OrderManager.placeOrder(
            items = orderItems,
            total = String.format(Locale.getDefault(), "$%.2f", total)
        )
        ProductSelectionManager.clearSelection()
        onPlaceOrderNavigate()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {

        GenericScreenHeader(onNavigateBackward = onNavigateBackward)

        Spacer(modifier = Modifier.height(16.dp))

        ShippingAddressCard()

        Spacer(modifier = Modifier.height(12.dp))

        PaymentMethodCard()

        Spacer(modifier = Modifier.height(12.dp))

        CheckoutSummaryCard(subtotal, shipping, tax, total)

        Spacer(modifier = Modifier.height(24.dp))

        PlaceOrderButton(
            enabled = cartItems.isNotEmpty(),
            onPlaceOrder = triggerPlaceOrder
        )
        
        // Extra padding at the bottom for better reachability
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ShippingAddressCard() {
    GenericCard {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = "Location")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Shipping Address", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text("Juan Perez del Campo")
            Text("Av. Siempre Viva 742")
            Text("Springfield, USA", color = Color.Gray)
        }
    }
}

@Composable
fun PaymentMethodCard() {
    GenericCard {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Card")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Payment Method", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text("Visa **** 1234")
            Text("Exp: 12/26", color = Color.Gray)
        }
    }
}

@Composable
fun CheckoutSummaryCard(subtotal: Double, shipping: Double, tax: Double, total: Double) {
    GenericCard {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "Order Summary",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            SummaryRow("Subtotal", String.format(Locale.getDefault(), "$%.2f", subtotal))
            SummaryRow("Shipping", String.format(Locale.getDefault(), "$%.2f", shipping))
            SummaryRow("Tax", String.format(Locale.getDefault(), "$%.2f", tax))
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total", style = MaterialTheme.typography.titleMedium)
                Text(
                    String.format(Locale.getDefault(), "$%.2f", total),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Composable
fun PlaceOrderButton(
    enabled: Boolean,
    onPlaceOrder: () -> Unit
) {
    Button(
        onClick = onPlaceOrder,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text("Place Order")
    }
}
