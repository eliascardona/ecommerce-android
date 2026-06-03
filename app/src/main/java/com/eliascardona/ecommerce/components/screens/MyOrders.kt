package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.components.layout.generic.GenericScreenHeader
import com.eliascardona.ecommerce.components.shared.card.GenericCard
import com.eliascardona.ecommerce.infrastructure.data.Order
import com.eliascardona.ecommerce.infrastructure.data.OrderItem
import com.eliascardona.ecommerce.infrastructure.data.OrderManager

@Composable
fun MyOrders(
    onNavigateBackward: () -> Unit
) {
    val orders = OrderManager.snapshot()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        GenericScreenHeader(onNavigateBackward = onNavigateBackward)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "My Orders",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (orders.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("You haven't placed any orders yet.", color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(orders) { order ->
                    OrderCard(order)
                }
            }
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Order #${order.id}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(order.date, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                }

                StatusBadge(order.status)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Items
            order.items.forEachIndexed { index, item ->
                OrderItemRow(item)

                if (index < order.items.size - 1) {
                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            // Total
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total", color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = order.total,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Actions
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionPillButton(
                    text = "Track",
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
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(item.name, style = MaterialTheme.typography.bodyMedium)
            Text("Qty: ${item.quantity}", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        }

        Text(item.price, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun StatusBadge(status: String) {
    val (bgColor, textColor) = when (status) {
        "Delivered" -> Color(0xFFE8F5E9) to Color(0xFF2E7D32)
        "Shipped" -> Color(0xFFE3F2FD) to Color(0xFF1976D2)
        "Processing" -> Color(0xFFFFF3E0) to Color(0xFFE65100)
        else -> Color(0xFFEDE7F6) to Color(0xFF5E35B1)
    }

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = bgColor
    ) {
        Text(
            text = status,
            color = textColor,
            style = MaterialTheme.typography.labelMedium,
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
        color = backgroundColor,
        modifier = Modifier.height(36.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = text, tint = contentColor, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(text, color = contentColor, style = MaterialTheme.typography.labelLarge)
        }
    }
}
