package com.example.mae.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.mae.ui.Data.Product
import kotlinx.android.synthetic.main.product_item.view.*

class ProductAdapter: RecyclerView.Adapter<ProductViewHolder>() {

    var data = listOf<Product>()
        set(value) {
            field = value
            notifyDataSetChanged()

        }
    override fun getItemCount()=data.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int)  {
        val item = data[position]
        holder.prodImg.setImageResource(when(item.prodImg){
            1->R.drawable.apple
            2->R.drawable.bnn
            3->R.drawable.chicken
            4->R.drawable.cl
            5->R.drawable.det
            6->R.drawable.jam
            7->R.drawable.jus
            8->R.drawable.milk
            else->R.drawable.chicken
        })
        holder.prodName.text = item.prodName
        holder.prodPrice.text = item.prodPrice.toString()
        holder.prodItem.setOnClickListener {
            val action = ProductFragmentDirections.actionNavigationProductsToProductDisplayFragment(item)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setdata(product: List<Product>){
        this.data = product
        notifyDataSetChanged()
    }
}