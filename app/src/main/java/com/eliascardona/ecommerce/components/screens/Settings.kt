package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.components.layout.generic.GenericScreenHeader

@Composable
fun Settings(
    onNavigateBackward: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        GenericScreenHeader(
            screenTitle = "Settings",
            onNavigateBackward = onNavigateBackward
        )
        Spacer(modifier = Modifier.height(16.dp))

        // ACCOUNT
        SectionTitle("Account")
        NavigationRow("Edit Profile", Icons.Default.Person)
        NavigationRow("Security", Icons.Default.Warning)
        NavigationRow("Payment Methods", Icons.Default.AddCircle)

        Spacer(modifier = Modifier.height(16.dp))


        // PREFERENCES
        SectionTitle("Preferences")
        ToggleRow("Push Notifications", Icons.Default.Notifications)
        ToggleRow("Biometric Authentication", Icons.Default.Lock)

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        color = Color.Gray,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun NavigationRow(
    text: String,
    icon: ImageVector
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 12.dp),
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

@Composable
fun ToggleRow(
    text: String,
    icon: ImageVector
) {
    var checked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(icon, contentDescription = text)

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            modifier = Modifier.weight(1f)
        )

        Switch(
            checked = checked,
            onCheckedChange = { checked = it }
        )
    }
}