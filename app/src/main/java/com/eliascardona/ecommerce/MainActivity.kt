package com.eliascardona.ecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eliascardona.ecommerce.components.layout.ShoppingHeader
import com.eliascardona.ecommerce.components.screens.Account
import com.eliascardona.ecommerce.ui.theme.EcommerceTheme
import com.eliascardona.ecommerce.components.screens.CheckoutScreen
import com.eliascardona.ecommerce.components.screens.Featured
import com.eliascardona.ecommerce.components.screens.HomeScreen
import com.eliascardona.ecommerce.components.screens.MyOrders
import com.eliascardona.ecommerce.components.screens.ProductDetails
import com.eliascardona.ecommerce.components.screens.Settings
import com.eliascardona.ecommerce.components.screens.ShoppingCart
import com.eliascardona.ecommerce.components.screens.SignInScreen
import com.eliascardona.ecommerce.components.screens.SignUpScreen
import com.eliascardona.ecommerce.components.shared.content_container.GenericContainer
import com.eliascardona.ecommerce.infrastructure.items_management.ProductDetailsManager
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
//    val context = LocalContext.current
//    val shoppingCartRepository = ShoppingCartRepository(context = context)

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val observer =
//            AppLifecycleObserver(
//                cartRepository = shoppingCartRepository
//            )
//
//        ProcessLifecycleOwner
//            .get()
//            .lifecycle
//            .addObserver(observer)

        auth = Firebase.auth
        firestore = Firebase.firestore

        setContent {
            EcommerceTheme {
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        ShoppingHeader(
                            title = "E-Commerce",
                            onNavigateToCart = {
                                navController.navigate("shopping_cart")
                            },
                            onNavigateToCheckout = {
                                navController.navigate("checkout")
                            }
                        )
                    },
                    bottomBar = {
                        BottomNavigationBar(navController)
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    ECommerceApp(
                        auth = auth,
                        firestore = firestore,
                        navController = navController,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}

@Composable
fun ECommerceApp(
    auth: FirebaseAuth,
    firestore: FirebaseFirestore,
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    GenericContainer(modifier = Modifier.padding(innerPadding)) {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("signUp") {
                SignUpScreen(
                    auth = auth,
                    firestoreDB = firestore,
                    navController = navController,
                    modifier = Modifier
                )
            }

            composable("signIn") {
                SignInScreen(
                    auth = auth,
                    navController = navController,
                    modifier = Modifier
                )
            }

            composable("home") {
                HomeScreen(
                    onNavigateToProductDetails = {
                        navController.navigate("product_details") { launchSingleTop = true }
                    }
                )
            }

            composable("featured") {
                Featured(
                    onProductItemClick = {
                        navController.navigate("product_details") { launchSingleTop = true }
                    }
                )
            }

            composable("product_details") {
                ProductDetails(
                    onNavigateBackward = {
                        navController.navigate("home") { launchSingleTop = true }
                        ProductDetailsManager.clearSelection()
                    }
                )
            }

            composable("shopping_cart") {
                ShoppingCart(
                    onNavigateToCheckout = {
                        navController.navigate("checkout") { launchSingleTop = true }
                    }
                )
            }

            composable("checkout") {
                CheckoutScreen(
                    onPlaceOrderNavigate = {
                        navController.navigate("shopping_cart") { launchSingleTop = true }
                    },
                    onNavigateBackward = {
                        navController.navigate("shopping_cart") { launchSingleTop = true }
                    }
                )
            }

            composable("my_orders") {
                MyOrders(
                    onNavigateBackward = {
                        navController.navigate("account") { launchSingleTop = true }
                    }
                )
            }

            composable("account") {
                Account(
                    onNavigateToOrders = {
                        navController.navigate("my_orders") { launchSingleTop = true }
                    },
                    onNavigateToSettings = {
                        navController.navigate("settings") { launchSingleTop = true }
                    },
                )
            }

            composable("settings") {
                Settings(
                    onNavigateBackward = {
                        navController.navigate("account") { launchSingleTop = true }
                    }
                )
            }

        }
    }
}

private data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("home", "Home", Icons.Default.Home),
        BottomNavItem("featured", "Featured", Icons.Default.Star),
        BottomNavItem("shopping_cart", "Cart", Icons.Default.ShoppingCart),
        BottomNavItem("account", "Account", Icons.Default.Person)
    )

    NavigationBar {
        val currentRoute =
            navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Avoid building huge back stack
                        popUpTo("home") {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "Clickable icon that navigates to ${item.label}"
                    )
                },
                label = {
                    Text(text = item.label)
                }
            )
        }
    }
}