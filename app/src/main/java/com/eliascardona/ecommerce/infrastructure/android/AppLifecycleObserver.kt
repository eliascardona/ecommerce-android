package com.eliascardona.ecommerce.infrastructure.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.eliascardona.ecommerce.components.features.product.sampleProducts
import com.eliascardona.ecommerce.domain.shopping_cart.ShoppingCartRepository
import com.eliascardona.ecommerce.infrastructure.data.ProductEntity
import com.eliascardona.ecommerce.infrastructure.data.ProductManager
import com.eliascardona.ecommerce.infrastructure.items_management.ProductSelectionManager
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppLifecycleObserver(
    private val cartRepository: ShoppingCartRepository
) : DefaultLifecycleObserver {

    override fun onStart(owner: LifecycleOwner) {
//        val db = Firebase.firestore
//        db.collection("products")
//            .get()
//            .addOnSuccessListener { result ->
//                val products = result.mapNotNull { document ->
//                    document.toObject(ProductEntity::class.java).copy(productId = document.id)
//                }
//                ProductManager.loadProducts(products)
//                Log.d("AppLifecycleObserver", "Fetched ${products.size} products from Firestore")
//            }
//            .addOnFailureListener { exception ->
//                Log.e("AppLifecycleObserver", "Error fetching products", exception)
//            }
        ProductManager.loadProducts(sampleProducts)
    }

    override fun onStop(owner: LifecycleOwner) {
        CoroutineScope(Dispatchers.IO).launch {
            cartRepository.persistCart(
                ProductSelectionManager.snapshot()
            )
        }
    }
}
