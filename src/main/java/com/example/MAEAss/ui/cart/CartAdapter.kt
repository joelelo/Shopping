package com.example.MAEAss.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.MAEAss.ui.data.Cart
import com.example.myapplication.R

class CartAdapter: RecyclerView.Adapter<CartViewHolder>() {
    var data = listOf<Cart>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    var list = mutableListOf<Cart>()




    override fun getItemCount() = data.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)

    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = data[position]
        holder.cartImg.setImageResource(when(item.img){
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
        holder.cartName.text = item.name
        holder.cartPrice.text = item.price.toString()
        holder.cartQuantity.setText(item.quantity.toString())

        holder.cartQuantity.doOnTextChanged { text, start, before, count ->
            if (text!!.isNotEmpty()) {
                if (holder.cartQuantity.hasFocus()) {
                    if(list[position].name==holder.cartName.text.toString()){
                        list[position]=Cart(list[position].name,list[position].img,list[position].price,Integer.parseInt(text.toString()))
                    }
                }
            }

        }
    }
    fun setdata(cart: List<Cart>){
        this.data = cart
        for(i in data){
            list.add(i)
        }
        notifyDataSetChanged()

    }

}