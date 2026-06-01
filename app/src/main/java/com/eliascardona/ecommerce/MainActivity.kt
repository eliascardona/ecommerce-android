package com.eliascardona.ecommerce

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eliascardona.ecommerce.components.layout.ShoppingHeader
import com.eliascardona.ecommerce.components.screens.Account
import com.eliascardona.ecommerce.ui.theme.EcommerceTheme
import com.eliascardona.ecommerce.components.screens.CheckoutForm
import com.eliascardona.ecommerce.components.screens.Featured
import com.eliascardona.ecommerce.components.screens.HomeScreen
import com.eliascardona.ecommerce.components.screens.MyOrders
import com.eliascardona.ecommerce.components.screens.ProductDetails
import com.eliascardona.ecommerce.components.screens.Settings
import com.eliascardona.ecommerce.components.screens.ShoppingCart
import com.eliascardona.ecommerce.components.shared.content_container.GenericContainer
import com.eliascardona.ecommerce.domain.shopping_cart.ShoppingCartRepository
import com.eliascardona.ecommerce.infrastructure.lifecycle.AppLifecycleObserver

class MainActivity : ComponentActivity() {
//    val context = LocalContext.current
//    val shoppingCartRepository = ShoppingCartRepository(context = context)

    @RequiresApi(Build.VERSION_CODES.O)
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

        setContent {
            EcommerceTheme {
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        ShoppingHeader(
                            title = "E-Commerce",
                            onNavigateToCart = {
                                navController.navigate("cart")
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
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    GenericContainer(modifier = Modifier.padding(innerPadding)) {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                HomeScreen(
                    onProductItemClick = {
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
                    onAddToCart = {
                        navController.navigate("shopping_cart") { launchSingleTop = true }
                    },
                    onNavigateBackward = {
                        navController.navigate("home") { launchSingleTop = true }
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
                CheckoutForm(
                    onPlaceOrder = {
                        navController.navigate("my_orders") { launchSingleTop = true }
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


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        "home",
        "featured",
        "account",
        "shopping_cart"
    )

    NavigationBar {
        val currentRoute =
            navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen,
                onClick = {
                    navController.navigate(screen) {

                        // Avoid building huge back stack
                        popUpTo("home")

                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = getIconForScreen(screen),
                        contentDescription = "Clickable icon that navigates to $screen"
                    )
                },
                label = {
                    Text(screen)
                }
            )
        }
    }
}


fun getIconForScreen(screen: String) = when (screen) {
    "home" -> Icons.Default.Home
    "featured" -> Icons.Default.Star
    "account" -> Icons.Default.Person
    "shopping_cart" -> Icons.Default.ShoppingCart
    else -> Icons.Default.Home
}