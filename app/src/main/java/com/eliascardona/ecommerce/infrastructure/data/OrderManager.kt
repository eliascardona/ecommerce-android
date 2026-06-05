package com.eliascardona.ecommerce.infrastructure.data

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object OrderManager {
    private val _orders = MutableStateFlow<List<Order>>(emptyList())

    fun snapshot(): List<Order> {
        return _orders.value.toList()
    }

    fun placeOrder(items: List<OrderItem>, total: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val user = Firebase.auth.currentUser
        if (user == null) {
            onFailure(Exception("User not logged in"))
            return
        }

        val db = Firebase.firestore
        
        // Fetch user profile from Firestore to populate order details
        db.collection("users").document(user.uid).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val fullName = document.getString("fullName") ?: ""
                    val shippingAddress = document.getString("shippingAddress") ?: ""
                    val email = document.getString("email") ?: user.email ?: ""

                    val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                    val newOrder = Order(
                        id = "ORD-${UUID.randomUUID().toString().take(6).uppercase()}",
                        date = dateFormat.format(Date()),
                        status = "Processing",
                        items = items,
                        total = total,
                        userId = user.uid,
                        userName = fullName,
                        userEmail = email,
                        shippingAddress = shippingAddress
                    )

                    // Save the order in the user's sub-collection
                    db.collection("users")
                        .document(user.uid)
                        .collection("orders")
                        .document(newOrder.id)
                        .set(newOrder)
                        .addOnSuccessListener {
                            Log.d("OrderManager", "Order successfully placed in Firestore with user details")
                            onSuccess()
                        }
                        .addOnFailureListener { e ->
                            Log.e("OrderManager", "Error placing order", e)
                            onFailure(e)
                        }
                } else {
                    onFailure(Exception("User profile not found"))
                }
            }
            .addOnFailureListener { e ->
                Log.e("OrderManager", "Error fetching user profile for order", e)
                onFailure(e)
            }
    }

    fun fetchOrders(onSuccess: (List<Order>) -> Unit, onFailure: (Exception) -> Unit) {
        val user = Firebase.auth.currentUser
        if (user == null) {
            onFailure(Exception("User not logged in"))
            return
        }

        val db = Firebase.firestore
        db.collection("users")
            .document(user.uid)
            .collection("orders")
            .get()
            .addOnSuccessListener { result ->
                val orders = result.mapNotNull { it.toObject(Order::class.java) }
                onSuccess(orders)
            }
            .addOnFailureListener { e ->
                Log.e("OrderManager", "Error fetching orders", e)
                onFailure(e)
            }
    }
}
