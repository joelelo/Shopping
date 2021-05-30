package com.example.mae.ui.dashboard

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.product_item.view.*

class ProductViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val prodImg:ImageView = itemView.prod_image
    val prodName:TextView = itemView.prod_name
    val prodPrice:TextView = itemView.prod_price
    val prodItem:ConstraintLayout = itemView.prod_item
}