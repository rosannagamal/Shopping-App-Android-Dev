package com.example.cs3130_assignment3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private var productDescriptionList: ArrayList<String>,
    private var productPriceList: ArrayList<String>,
    private var productImageList: ArrayList<Int>,
    private val onItemClick: (String, String, Int) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.products_list_design, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.descriptionTextView.text = productDescriptionList.get(position)
        holder.priceTextView.text = productPriceList.get(position)
        holder.productImageView.setImageResource(productImageList.get(position))

        holder.cardView.setOnClickListener {
            onItemClick(productDescriptionList[position], productPriceList[position], productImageList[position])
        }
    }

    override fun getItemCount(): Int {
        return productDescriptionList.size
    }

    class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var descriptionTextView: TextView = itemView.findViewById(R.id.productDescription)
        var priceTextView: TextView = itemView.findViewById(R.id.productPrice)
        var productImageView: ImageView = itemView.findViewById(R.id.productImage)
        var cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}