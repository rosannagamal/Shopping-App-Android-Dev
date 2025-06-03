package com.example.cs3130_assignment3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val items: List<CartItem>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImageView)
        val description: TextView = itemView.findViewById(R.id.descriptionTextView)
        val quantity: TextView = itemView.findViewById(R.id.quantityTextView)
        val price: TextView = itemView.findViewById(R.id.priceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_design, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        holder.productImage.setImageResource(item.imageResId)
        holder.description.text = item.description
        holder.quantity.text = "Quantity: ${item.quantity}"
        val priceQuantity = item.price.toInt() * item.quantity
        holder.price.text = "${priceQuantity} egp"
    }

    override fun getItemCount(): Int = items.size
}

data class CartItem(
    val imageResId: Int,
    val description: String,
    val price: String,
    val quantity: Int
)

