package com.example.MAEAss.ui.cart

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cart_item.view.*

class CartViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val cartImg:ImageView = itemView.ivCart
    val cartName:TextView = itemView.tvNameCart
    val cartPrice:TextView = itemView.tvPriceCart
    val cartQuantity:EditText = itemView.etQuantity
}