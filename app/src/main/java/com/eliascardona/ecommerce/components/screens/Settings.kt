package com.eliascardona.ecommerce.components.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.eliascardona.ecommerce.components.layout.generic.GenericScreenHeader

// -----------------------------
// SCREEN
// -----------------------------
@Composable
fun Settings(
    onNavigateBackward: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        GenericScreenHeader(onNavigateBackward = onNavigateBackward)

        Text(
            text = "Settings",
            style = MaterialTheme.typography.titleLarge
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
        ToggleRow("Dark Mode", Icons.Default.ArrowDropDown)
        ToggleRow("Biometric Authentication", Icons.Default.Lock)
        ToggleRow("Email Notifications", Icons.Default.Email)

        Spacer(modifier = Modifier.height(16.dp))

        // MORE
        SectionTitle("More")

        NavigationRow("Help & Support", Icons.Default.Info)
    }
}


// -----------------------------
// SECTION TITLE
// -----------------------------
@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        color = Color.Gray,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

// -----------------------------
// NAVIGATION ROW
// -----------------------------
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
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Go",
            tint = Color.Gray
        )
    }
}

// -----------------------------
// TOGGLE ROW
// -----------------------------
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