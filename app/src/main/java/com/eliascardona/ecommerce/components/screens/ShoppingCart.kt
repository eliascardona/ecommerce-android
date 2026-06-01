package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.R
import com.eliascardona.ecommerce.components.shared.card.GenericCard
import com.eliascardona.ecommerce.infrastructure.data.CartItem

// STATIC DATA
val sampleCartItems = listOf(
    CartItem(
        "Nike Air Max 270",
        "US 9",
        15.99,
        1,
    )
)

// -----------------------------
// SCREEN
// -----------------------------
@Composable
fun ShoppingCart(
    onNavigateToCheckout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Shopping Cart",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "${sampleCartItems.size} items",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        sampleCartItems.forEach {
            CartItemCard(it)
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        OrderSummaryCard(onNavigateToCheckout = onNavigateToCheckout)
    }
}

@Composable
fun CartItemCard(item: CartItem) {
    GenericCard {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.shoe1),
                contentDescription = item.name,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(item.name)
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red
                    )
                }

                Text("Quantity: ${item.quantity}", color = Color.Gray)

                Text(
                    "$${item.unitPrice}",
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                QuantitySelector(item.quantity)
            }
        }
    }
}

// -----------------------------
// QUANTITY SELECTOR
// -----------------------------
@Composable
fun QuantitySelector(quantity: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        CircleButton(Icons.Default.Clear)

        Spacer(modifier = Modifier.width(12.dp))

        Text(quantity.toString())

        Spacer(modifier = Modifier.width(12.dp))

        CircleButton(Icons.Default.Add)
    }
}

@Composable
fun CircleButton(icon: ImageVector) {
    Surface(
        shape = CircleShape,
        modifier = Modifier.size(40.dp),
        border = ButtonDefaults.outlinedButtonBorder
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = icon,
                contentDescription = "Action"
            )
        }
    }
}

// -----------------------------
// ORDER SUMMARY
// -----------------------------
@Composable
fun OrderSummaryCard(
    onNavigateToCheckout: () -> Unit
) {
    GenericCard {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                "Order Summary",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            SummaryRow("Subtotal", "$519.97")
            SummaryRow("Shipping", "$10.00")
            SummaryRow("Tax", "$52.00")

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

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

                Button(
                    onClick = onNavigateToCheckout,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .height(56.dp)
                        .width(180.dp)
                ) {
                    Text("Go to checkout")
                }
            }
        }
    }
}

@Composable
fun SummaryRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray)
        Text(value)
    }
}