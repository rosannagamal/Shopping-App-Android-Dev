package com.example.cs3130_assignment3

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartAdapter
    private lateinit var subtotalTextView: TextView
    private lateinit var totalTextView: TextView
    private lateinit var clearCartButton: Button
    private val cartItems = mutableListOf<CartItem>()

    private lateinit var sharedPreferences: SharedPreferences

    private var subtotal: Int = 0
    private var total: Int = 0
    private var shipping: Int = 60

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.cartRecyclerView)
        subtotalTextView = view.findViewById(R.id.subTotalTextView)
        totalTextView = view.findViewById(R.id.totalTextView)
        clearCartButton = view.findViewById(R.id.clearCartButton)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CartAdapter(cartItems)
        recyclerView.adapter = adapter

        clearCartButton.setOnClickListener {
            cartItems.clear()
            adapter.notifyDataSetChanged()

            subtotal = 0
            total = 0
            subtotalTextView.text = "0 egp"
            totalTextView.text = "0 egp"

            sharedPreferences.edit().clear().apply()
        }
        sharedPreferences = requireContext().getSharedPreferences("cart_pref", Context.MODE_PRIVATE)
        retrieveData()
    }

    fun addToCart(description: String, price: String, imageResId: Int, quantity: Int) {
        val item = CartItem(imageResId, description, price, quantity)
        val priceInt = price.toIntOrNull() ?: 0

        cartItems.add(item)
        adapter.notifyItemInserted(cartItems.size - 1)

        subtotal += priceInt * quantity
        subtotalTextView.text = "$subtotal EGP"
        total = subtotal + shipping
        totalTextView.text = "$total EGP"

        if (subtotal > 200) {
            startNotification(requireContext())
        }
    }

    private fun saveData() {
        val editor = sharedPreferences.edit()
        editor.putInt("cart_size", cartItems.size)

        for ((index, item) in cartItems.withIndex()) {
            editor.putString("item_${index}_description", item.description)
            editor.putString("item_${index}_price", item.price)
            editor.putInt("item_${index}_image", item.imageResId)
            editor.putInt("item_${index}_quantity", item.quantity)
        }
        editor.putInt("cart_subtotal", subtotal)
        editor.putInt("cart_total", total)
        editor.apply()
    }

    private fun retrieveData() {
        cartItems.clear()
        val size = sharedPreferences.getInt("cart_size", 0)
        subtotal = sharedPreferences.getInt("cart_subtotal", 0)
        total = sharedPreferences.getInt("cart_total", subtotal + shipping)

        for (i in 0 until size) {
            val description = sharedPreferences.getString("item_${i}_description", null)
            val price = sharedPreferences.getString("item_${i}_price", null)
            val imageRes = sharedPreferences.getInt("item_${i}_image", -1)
            val quantity = sharedPreferences.getInt("item_${i}_quantity", 0)

            if (description != null && price != null && imageRes != -1) {
                cartItems.add(CartItem(imageRes, description, price, quantity))
            }
        }
        adapter.notifyDataSetChanged()

        subtotalTextView.text = "$subtotal egp"
        totalTextView.text = "$total egp"
    }

    private fun startNotification(context: Context) {
        val channel = NotificationChannel("cart_channel", "Shopping Cart Notifications", NotificationManager.IMPORTANCE_HIGH)

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(context, "cart_channel")
            .setSmallIcon(R.drawable.notiification)
            .setContentTitle("Cart Notification")
            .setContentText("Your total amount exceeded 200egp. Click to add more cakes.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        manager.notify(1, notification)
    }

    override fun onStart() {
        super.onStart()
        sharedPreferences = requireContext().getSharedPreferences("cart_pref", Context.MODE_PRIVATE)
        retrieveData()
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }

    override fun onStop() {
        super.onStop()
        saveData()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveData()
    }
}
