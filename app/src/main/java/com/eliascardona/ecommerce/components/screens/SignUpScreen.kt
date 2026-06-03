package com.eliascardona.ecommerce.components.screens
//
//import android.content.ContentValues.TAG
//import android.util.Log
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.imePadding
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.window.Dialog
//import androidx.navigation.NavController
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//
//@Composable
//fun SignUpScreen(
//    auth: FirebaseAuth,
//    firestoreDB: FirebaseFirestore,
//    navController: NavController,
//    modifier: Modifier = Modifier
//) {
//    var fullName by remember { mutableStateOf("") }
//    var shippingAddress by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    var showDialog by remember { mutableStateOf(false) }
//    var message by remember { mutableStateOf("") }
//
//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .padding(16.dp)
//            .imePadding(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        Text(
//            text = "Full name",
//            style = MaterialTheme.typography.titleLarge
//        )
//
//        Spacer(Modifier.height(16.dp))
//
//        TextField(
//            value = fullName,
//            onValueChange = { fullName = it },
//            modifier = Modifier.fillMaxSize()
//        )
//
//        Text(
//            text = "Shipping address",
//            style = MaterialTheme.typography.titleLarge
//        )
//
//        Spacer(Modifier.height(16.dp))
//
//        TextField(
//            value = shippingAddress,
//            onValueChange = { shippingAddress = it },
//            modifier = Modifier.fillMaxSize()
//        )
//
//        Text(
//            text = "Email",
//            style = MaterialTheme.typography.titleLarge
//        )
//
//        Spacer(Modifier.height(16.dp))
//
//        TextField(
//            value = email,
//            onValueChange = { email = it },
//            modifier = Modifier.fillMaxSize()
//        )
//
//        Spacer(Modifier.height(16.dp))
//
//        Text(
//            text = "Password",
//            style = MaterialTheme.typography.titleLarge
//        )
//
//        Spacer(Modifier.height(16.dp))
//
//        TextField(
//            value = password,
//            onValueChange = { password = it },
//            modifier = Modifier.fillMaxSize()
//        )
//
//        Spacer(Modifier.height(16.dp))
//
//        Button(
//            onClick = {
//                auth
//                    .createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener { task ->
//                        if(task.isSuccessful) {
//                            message = "You've successfully Signed Up!"
//
//                            val user = task.result.user
//                            val userId = user?.uid.toString()
//
//                            if (user != null) {
//                                val userHasMap = hashMapOf(
//                                    "id" to userId,
//                                    "email" to user.email,
//                                    "fullName" to fullName,
//                                    "shippingAddress" to shippingAddress,
//                                )
//
//                                firestoreDB.collection("users")
//                                    .document(userId)
//                                    .set(userHasMap)
//                                    .addOnSuccessListener {
//                                        Log.d(TAG, "DocumentSnapshot successfully written")
//
//                                        navController.navigate("home")
//
//                                    }
//                                    .addOnFailureListener { e ->
//                                        Log.w(TAG, "Error adding document", e)
//                                    }
//
//                            }
//
//                        } else {
//                            message = "An unexpected error occurred"
//                        }
//
//                        showDialog = true
//                    }
//            }
//        ) {
//            Text("Sign up!")
//        }
//
//        if (showDialog) {
//            Dialog(
//                onDismissRequest = { showDialog = false }
//            ) {
//                Text(message)
//            }
//        }
//    }
//}