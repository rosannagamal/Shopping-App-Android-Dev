package com.example.cs3130_assignment3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class SelectedProductFragment : Fragment(R.layout.fragment_selected_product) {
    private lateinit var descTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var increaseButton: Button
    private lateinit var decreaseButton: Button
    private lateinit var quantityTextView: TextView
    private lateinit var addToCartButton: Button

    private var quantity: Int = 0

    private var cartListener: OnAddToCartListener? = null

    interface OnAddToCartListener {
        fun onAddToCart(description: String?, price: String?, imageRes: Int?, quantity: Int)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val desc = arguments?.getString("desc")
        val price = arguments?.getString("price")
        val image = arguments?.getInt("image")

        descTextView = view.findViewById(R.id.descriptionTextView)
        priceTextView = view.findViewById(R.id.priceTextView)
        imageView = view.findViewById(R.id.productImageView)
        increaseButton = view.findViewById(R.id.increaseButton)
        decreaseButton = view.findViewById(R.id.decreaseButton)
        quantityTextView = view.findViewById(R.id.quantityTextView)
        addToCartButton = view.findViewById(R.id.addToCartButton)

        descTextView.text = desc
        priceTextView.text = "$price egp"
        imageView.setImageResource(image ?: 0)

        increaseButton.setOnClickListener {
            quantity++
            quantityTextView.text = quantity.toString()
        }

        decreaseButton.setOnClickListener {
            if (quantity > 0) {
                quantity--
                quantityTextView.text = quantity.toString()
            }
        }

        addToCartButton.setOnClickListener {
            if (quantity > 0) {
                cartListener?.onAddToCart(desc, price, image, quantity)
            } else {
                Toast.makeText(requireContext(), "Select quantity!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onAttach(context: android.content.Context) {
        super.onAttach(context)
        if (context is OnAddToCartListener) {
            cartListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        cartListener = null
    }
}