package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.components.layout.generic.GenericScreenHeader
import com.eliascardona.ecommerce.components.shared.card.GenericCard
import com.eliascardona.ecommerce.infrastructure.data.Order
import com.eliascardona.ecommerce.infrastructure.data.OrderItem

// STATIC DATA
val sampleOrders = listOf(
    Order(
        id = "ORD001",
        date = "14 de enero de 2024",
        status = "Delivered",
        items = listOf(
            OrderItem("Classic White Sneakers", 1, "$89.99"),
            OrderItem("Leather Crossbody Bag", 1, "$129.99")
        ),
        total = "$219.98"
    ),
    Order(
        id = "ORD002",
        date = "9 de enero de 2024",
        status = "Shipped",
        items = listOf(
            OrderItem("Denim Jacket", 1, "$79.99")
        ),
        total = "$79.99"
    ),
    Order(
        id = "ORD003",
        date = "4 de enero de 2024",
        status = "Processing",
        items = listOf(
            OrderItem("Cotton T-Shirt", 2, "$19.99")
        ),
        total = "$39.98"
    )
)

// SCREEN
@Composable
fun MyOrders(
    onNavigateBackward: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        GenericScreenHeader(onNavigateBackward = onNavigateBackward)

        Spacer(modifier = Modifier.height(16.dp))

        // Orders list
        sampleOrders.forEach {
            OrderCard(it)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun OrderCard(order: Order) {
    GenericCard {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            // Header row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Order #${order.id}")
                    Text(order.date, color = Color.Gray)
                }

                StatusBadge(order.status)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Items
            order.items.forEachIndexed { index, item ->
                OrderItemRow(item)

                if (index < order.items.size - 1) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Divider()

            Spacer(modifier = Modifier.height(12.dp))

            // Total
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total", color = Color.Gray)
                Text(order.total, style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Actions
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionPillButton(
                    text = "Track Order",
                    icon = Icons.Default.LocationOn,
                    backgroundColor = Color(0xFFE3F2FD),
                    contentColor = Color(0xFF1976D2)
                )

                ActionPillButton(
                    text = "Details",
                    icon = Icons.Default.AddCircle,
                    backgroundColor = Color(0xFFE8F5E9),
                    contentColor = Color(0xFF2E7D32)
                )
            }
        }
    }
}

@Composable
fun OrderItemRow(item: OrderItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(item.name)
            Text("Qty: ${item.quantity}", color = Color.Gray)
        }

        Text(item.price)
    }
}

@Composable
fun StatusBadge(status: String) {

    val (bgColor, textColor) = when (status) {
        "Delivered" -> Color(0xFFE8F5E9) to Color(0xFF2E7D32)
        "Shipped" -> Color(0xFFE3F2FD) to Color(0xFF1976D2)
        else -> Color(0xFFEDE7F6) to Color(0xFF5E35B1)
    }

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = bgColor
    ) {
        Text(
            text = status,
            color = textColor,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun ActionPillButton(
    text: String,
    icon: ImageVector,
    backgroundColor: Color,
    contentColor: Color
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = text, tint = contentColor)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text, color = contentColor)
        }
    }
}