package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.R

// -----------------------------
// SCREEN
// -----------------------------
@Composable
fun Account(
    onNavigateToOrders: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        ProfileHeader()

        Spacer(modifier = Modifier.height(16.dp))

        ProfileStats()

        Spacer(modifier = Modifier.height(16.dp))

        NavigationRow(
            text = "My Orders",
            icon = Icons.Default.ShoppingCart,
            onClick = onNavigateToOrders
        )

        NavigationRow(
            text = "Settings",
            icon = Icons.Default.Settings,
            onClick = onNavigateToSettings
        )
    }
}

// -----------------------------
// HEADER
// -----------------------------
@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box {
            Image(
                painter = painterResource(id = R.drawable.shoe1),
                contentDescription = "Profile image",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )

            // Small camera button (mock)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Info,
                    contentDescription = "Edit photo",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "John Doe",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "john.doe@example.com",
            color = Color.Gray
        )
    }
}

// -----------------------------
// STATS
// -----------------------------
@Composable
fun ProfileStats() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatItem("12", "Orders", Icons.Default.ShoppingCart)
        StatItem("24", "Wishlist", Icons.Default.Favorite)
        StatItem("8", "Reviews", Icons.Default.Star)
    }
}

@Composable
fun StatItem(value: String, label: String, icon: ImageVector) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFE3F2FD)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = label, tint = Color(0xFF1976D2))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(value, style = MaterialTheme.typography.titleMedium)
        Text(label, color = Color.Gray)
    }
}

// -----------------------------
// NAVIGATION ROW
// -----------------------------
@Composable
fun NavigationRow(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(icon, contentDescription = text)

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Go",
            tint = Color.Gray
        )
    }
}