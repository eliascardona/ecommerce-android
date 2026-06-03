package com.eliascardona.ecommerce.components.screens
//
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.imePadding
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.AlertDialog
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
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.window.Dialog
//import androidx.navigation.NavController
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.android.gms.common.api.ApiException
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.GoogleAuthProvider
//import com.eliascardona.ecommerce.R
//
//@Composable
//fun SignInScreen(
//    auth: FirebaseAuth,
//    navController: NavController,
//    modifier: Modifier = Modifier
//) {
//    val context = LocalContext.current
//    val token = stringResource(R.string.default_web_client_id)
//
//    var currentUser by remember { mutableStateOf(auth.currentUser) }
//
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    var showDialog by remember { mutableStateOf(false) }
//    var message by remember { mutableStateOf("") }
//
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts
//            .StartActivityForResult()
//    ) { result ->
//        val data = result.data
//
//        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//        val account = task.getResult(ApiException::class.java)
//        val credential = GoogleAuthProvider.getCredential(
//            account.idToken,
//            null
//        )
//
//        auth
//            .signInWithCredential(credential)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    message = "You are about to login using Google, ok?"
//
//                    navController.navigate("home")
//
//                } else {
//                    message = "An unexpected error occurred while trying to sign in with google"
//                }
//
//                showDialog = true
//                currentUser = auth.currentUser
//            }
//    }
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
//                    .signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener { task ->
//                        message = if(task.isSuccessful) {
//                            "You've successfully Signed Up!"
//                        } else {
//                            "An unexpected error occurred"
//                        }
//
//                        showDialog = true
//                    }
//            }
//        ) {
//            Text("Sign in")
//        }
//
//        Button(
//            onClick = {
//                val options = GoogleSignInOptions.Builder(
//                    GoogleSignInOptions.DEFAULT_SIGN_IN
//                )
//                    .requestIdToken(token)
//                    .requestEmail()
//                    .build()
//
//                val googleSignInClient = GoogleSignIn.getClient(
//                    context,
//                    options
//                )
//
//                launcher.launch(input = googleSignInClient.signInIntent)
//            }
//        ) {
//            Text("Login using your Google Account")
//        }
//
//        if (showDialog) {
//            Dialog(
//                onDismissRequest = { showDialog = false }
//            ) {
//                Text(message)
//            }
//        }
//
//        if (currentUser != null) {
//            AlertDialog(
//                onDismissRequest = { },
//                title = { Text("Welcome user") },
//                text = {
//                    Text("User login")
//                },
//                confirmButton = {
//                    Button(onClick = { }) {
//                        Text("Confirm")
//                    }
//                },
//                dismissButton = {
//                    Button(onClick = {
//                        showDialog = true
//                        message = "User logout"
//
//                        auth.signOut()
//                        currentUser = auth.currentUser
//                    }) {
//                        Text("Log out")
//                    }
//                }
//            )
//        }
//    }
//}