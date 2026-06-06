package com.eliascardona.ecommerce.components.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay

@Composable
fun SignUpScreen(
    auth: FirebaseAuth,
    firestoreDB: FirebaseFirestore,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val currentUser = auth.currentUser

    if (currentUser != null) {
        var countdown by remember { mutableIntStateOf(5) }
        
        LaunchedEffect(Unit) {
            while (countdown > 0) {
                delay(1000L)
                countdown--
            }
            navController.navigate("home") {
                popUpTo("signUp") { inclusive = true }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Active Session Detected",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Redirecting in $countdown seconds...",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    } else {
        var fullName by remember { mutableStateOf("") }
        var shippingAddress by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        var showDialog by remember { mutableStateOf(false) }
        var message by remember { mutableStateOf("") }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .imePadding()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Create an account",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Full name",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(16.dp))

            TextField(
                value = fullName,
                onValueChange = { fullName = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Shipping address",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(16.dp))

            TextField(
                value = shippingAddress,
                onValueChange = { shippingAddress = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Email",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Password",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    auth
                        .createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                message = "You've successfully Signed Up!"

                                val user = task.result.user
                                val userId = user?.uid.toString()

                                if (user != null) {
                                    val userHasMap = hashMapOf(
                                        "id" to userId,
                                        "email" to user.email,
                                        "fullName" to fullName,
                                        "shippingAddress" to shippingAddress,
                                    )

                                    firestoreDB.collection("users")
                                        .document(userId)
                                        .set(userHasMap)
                                        .addOnSuccessListener {
                                            Log.d(TAG, "DocumentSnapshot successfully written")
                                            navController.navigate("home")
                                        }
                                        .addOnFailureListener { e ->
                                            Log.w(TAG, "Error adding document", e)
                                        }
                                }
                            } else {
                                message = "An unexpected error occurred: ${task.exception?.message}"
                            }
                            showDialog = true
                        }
                }
            ) {
                Text("Sign up!")
            }

            Text(
                text = "Do you already have an account? Click here",
                modifier = Modifier
                    .clickable(onClick = {
                        navController.navigate("signIn")
                    }),
                textDecoration = TextDecoration.Underline
            )

            if (showDialog) {
                Dialog(
                    onDismissRequest = { showDialog = false }
                ) {
                    Card(modifier = Modifier.padding(16.dp)) {
                        Text(modifier = Modifier.padding(16.dp), text = message)
                    }
                }
            }
        }
    }
}
