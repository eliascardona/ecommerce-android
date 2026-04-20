package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

// SCREEN
@Composable
fun CheckoutForm(
    onPlaceOrder: () -> Unit,
    onNavigateBackward: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        GenericScreenHeader(onNavigateBackward = onNavigateBackward)

        Spacer(modifier = Modifier.height(16.dp))

        ShippingAddressCard()

        Spacer(modifier = Modifier.height(12.dp))

        PaymentMethodCard()

        Spacer(modifier = Modifier.height(12.dp))

        CheckoutSummaryCard()

        Spacer(modifier = Modifier.height(16.dp))

        PlaceOrderButton(onPlaceOrder = onPlaceOrder)
    }
}

// -----------------------------
// SHIPPING ADDRESS
// -----------------------------
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

// -----------------------------
// PAYMENT METHOD
// -----------------------------
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

// -----------------------------
// SUMMARY
// -----------------------------
@Composable
fun CheckoutSummaryCard() {
    GenericCard {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                "Order Summary",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            SummaryRow("Subtotal", "$519.97")
            SummaryRow("Shipping", "$10.00")
            SummaryRow("Tax", "$52.00")

            Spacer(modifier = Modifier.height(12.dp))

            Divider()

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total", style = MaterialTheme.typography.titleMedium)
                Text(
                    "$581.97",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

// -----------------------------
// PLACE ORDER BUTTON
// -----------------------------
@Composable
fun PlaceOrderButton(
    onPlaceOrder: () -> Unit
) {
    Button(
        onClick = onPlaceOrder,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text("Place Order")
    }
}