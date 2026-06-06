package com.eliascardona.ecommerce.components.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.eliascardona.ecommerce.R
import kotlinx.coroutines.delay

@Composable
fun SignInScreen(
    auth: FirebaseAuth,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val token = stringResource(R.string.default_web_client_id)
    val currentUser = auth.currentUser

    if (currentUser != null) {
        var countdown by remember { mutableIntStateOf(5) }
        LaunchedEffect(Unit) {
            while (countdown > 0) {
                delay(1000L)
                countdown--
            }
            navController.navigate("home") {
                popUpTo("signIn") { inclusive = true }
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
                        color = MaterialTheme.colorScheme.primaryContainer,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Redirecting you in $countdown seconds...",
                        color = MaterialTheme.colorScheme.primaryContainer,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    } else {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        var showDialog by remember { mutableStateOf(false) }
        var message by remember { mutableStateOf("") }

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                auth.signInWithCredential(credential)
                    .addOnCompleteListener { authTask ->
                        if (authTask.isSuccessful) {
                            navController.navigate("home")
                        } else {
                            message = "Google sign in failed"
                            showDialog = true
                        }
                    }
            } catch (e: ApiException) {
                message = "Google API Error: ${e.message}"
                showDialog = true
            }
        }

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
                text = "Log into your account",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Email",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(8.dp))

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
            Spacer(Modifier.height(8.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(24.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                navController.navigate("home")
                            } else {
                                message = "Login failed: ${task.exception?.message}"
                                showDialog = true
                            }
                        }
                }
            ) {
                Text("Sign in")
            }

            Spacer(Modifier.height(8.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(token)
                        .requestEmail()
                        .build()
                    val googleSignInClient = GoogleSignIn.getClient(context, options)
                    launcher.launch(googleSignInClient.signInIntent)
                }
            ) {
                Text("Sign in with Google!")
            }

            Text(
                text = "Do you not have an account? Click here",
                modifier = Modifier
                    .clickable(onClick = {
                        navController.navigate("signUp")
                    }),
                textDecoration = TextDecoration.Underline
            )

            if (showDialog) {
                Dialog(onDismissRequest = { showDialog = false }) {
                    Card(modifier = Modifier.padding(16.dp)) {
                        Text(modifier = Modifier.padding(16.dp), text = message)
                    }
                }
            }
        }
    }
}
