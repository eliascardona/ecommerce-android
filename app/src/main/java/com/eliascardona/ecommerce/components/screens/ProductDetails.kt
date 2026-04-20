package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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

// -----------------------------
// SCREEN
// -----------------------------
@Composable
fun ProductDetails(
    onAddToCart: () -> Unit,
    onNavigateBackward: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        ProductImageHeader(onNavigateBackward = onNavigateBackward)

        ProductDetailsBody()

        ProductBottomBar(onAddToCart = onAddToCart)
    }
}

// -----------------------------
// HEADER (IMAGE + ACTIONS)
// -----------------------------
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
                boxModifier = Modifier.clickable(
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
fun CircleIconButton(icon: ImageVector, boxModifier: Modifier = Modifier) {
    Surface(
        shape = CircleShape,
        color = Color.White,
        modifier = Modifier.size(48.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = boxModifier
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Icon"
            )
        }
    }
}

// -----------------------------
// BODY
// -----------------------------
@Composable
fun ProductDetailsBody() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        IndicatorDots()

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Nike",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Text(
            text = "Nike Air Max 270",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("⭐ 4.5", color = Color(0xFFFFC107))
            Spacer(modifier = Modifier.width(8.dp))
            Text("(128 reviews)", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "$159.99",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Divider()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Select Size",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        SizeSelector()
    }
}

// -----------------------------
// INDICATOR DOTS
// -----------------------------
@Composable
fun IndicatorDots() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(3) { index ->
            val color = if (index == 0) Color.Blue else Color.LightGray

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(8.dp)
                    .background(color, CircleShape)
            )
        }
    }
}

// -----------------------------
// SIZE SELECTOR
// -----------------------------
@Composable
fun SizeSelector() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SizeButton("US 7", selected = false)
        SizeButton("US 8", selected = true)
        SizeButton("US 9", selected = false)
    }
}

@Composable
fun SizeButton(text: String, selected: Boolean) {
    val backgroundColor =
        if (selected) MaterialTheme.colorScheme.primary else Color.Transparent

    val contentColor =
        if (selected) MaterialTheme.colorScheme.onPrimary else Color.Black

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor,
        modifier = Modifier
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = contentColor)
        }
    }
}

// -----------------------------
// BOTTOM BAR
// -----------------------------
@Composable
fun ProductBottomBar(
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
            Text("Total:", color = Color.Gray)
            Text(
                "$159.99",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Button(
            onClick = onAddToCart,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .height(56.dp)
                .width(180.dp)
        ) {
            Text("Add to Cart")
        }
    }
}