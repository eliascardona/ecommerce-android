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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eliascardona.ecommerce.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun Account(
    auth: FirebaseAuth,
    firestore: FirebaseFirestore,
    navController: NavController,
    onNavigateToOrders: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    // State to observe the current authenticated user
    var currentUser by remember { mutableStateOf(auth.currentUser) }
    var userFullName by remember { mutableStateOf<String?>(null) }

    // Listener to update the state whenever the auth state changes (login/logout)
    DisposableEffect(auth) {
        val listener = FirebaseAuth.AuthStateListener {
            currentUser = it.currentUser
        }
        auth.addAuthStateListener(listener)
        onDispose {
            auth.removeAuthStateListener(listener)
        }
    }

    // Fetch user details from Firestore when currentUser changes
    LaunchedEffect(currentUser) {
        val uid = currentUser?.uid
        if (uid != null) {
            firestore.collection("users").document(uid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        userFullName = document.getString("fullName")
                    }
                }
        } else {
            userFullName = null
        }
    }

    // If no user is logged in, show the SignUpScreen
    if (currentUser == null) {
        SignUpScreen(
            auth = auth,
            firestoreDB = firestore,
            navController = navController
        )
    } else {
        // Main Account UI for logged-in users
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ProfileHeader(
                name = userFullName ?: "User Profile",
                email = currentUser?.email ?: ""
            )

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

            Spacer(modifier = Modifier.weight(1f))

            // Logout button
            Button(
                onClick = { auth.signOut() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Log out")
            }
        }
    }
}

@Composable
fun ProfileHeader(name: String, email: String) {
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
            text = name,
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = email,
            color = Color.Gray
        )
    }
}

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
