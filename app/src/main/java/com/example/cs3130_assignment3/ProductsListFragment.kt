package com.example.cs3130_assignment3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductsListFragment : Fragment(R.layout.fragment_products_list) {

    lateinit var recyclerView: RecyclerView
    private var productDescriptionList = ArrayList<String>()
    private var productPriceList = ArrayList<String>()
    private var productImageList = ArrayList<Int>()

    lateinit var adapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.cartRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        productDescriptionList.add(getString(R.string.confetti_cake))
        productDescriptionList.add(getString(R.string.banana_cake))
        productDescriptionList.add(getString(R.string.chocolate_cake))
        productDescriptionList.add(getString(R.string.cheesecake))
        productDescriptionList.add(getString(R.string.doggie_cake))
        productDescriptionList.add(getString(R.string.red_velvet_cake))
        productDescriptionList.add(getString(R.string.easter_cake))

        productImageList.add(R.drawable.confetti)
        productImageList.add(R.drawable.banana)
        productImageList.add(R.drawable.chocolate)
        productImageList.add(R.drawable.cheesecake)
        productImageList.add(R.drawable.doggie_cake)
        productImageList.add(R.drawable.redvelvet)
        productImageList.add(R.drawable.easter_cake)

        productPriceList.add("600")
        productPriceList.add("100")
        productPriceList.add("400")
        productPriceList.add("500")
        productPriceList.add("600")
        productPriceList.add("300")
        productPriceList.add("400")

        adapter = ProductAdapter(productDescriptionList, productPriceList, productImageList) {
            desc, price, imageRes ->
            val fragment = SelectedProductFragment()
            val bundle = Bundle()
            bundle.putString("desc", desc)
            bundle.putString("price", price)
            bundle.putInt("image", imageRes)
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.bottomFrame, fragment)
                .commit()
        }
        recyclerView.adapter = adapter
    }
}