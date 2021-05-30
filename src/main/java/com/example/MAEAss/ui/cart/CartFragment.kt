package com.example.mae.ui.notifications

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.MAEAss.ui.data.Cart
import com.example.MAEAss.ui.cart.CartAdapter
import com.example.MAEAss.ui.cart.CartViewModelFactory
import com.example.mae.ui.Data.AppDatabase
import com.example.mae.ui.Data.ProductDAO
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding
    lateinit var application: Application
    lateinit var dataSource: ProductDAO
    lateinit var viewModelFactory: CartViewModelFactory
    private lateinit var cartViewModel:CartViewModel
    lateinit var adapter:CartAdapter


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)


        application = requireNotNull(this.activity).application

        dataSource = AppDatabase.getInstance(application).productDAO
        viewModelFactory = CartViewModelFactory(dataSource, application)


        cartViewModel =
                ViewModelProvider(this,viewModelFactory).get(CartViewModel::class.java)

        binding.cartViewModel = cartViewModel

        binding.lifecycleOwner=this

        adapter = CartAdapter()
        binding.recyclerviewc.adapter = adapter
        binding.recyclerviewc.layoutManager = LinearLayoutManager(requireContext())

        binding.btnSaveCart.setOnClickListener {
//            binding.tvTotal.text = "0"
            verifyList(adapter.list,adapter.data)

        }
        var total = 0
        cartViewModel.getTotal().observe(viewLifecycleOwner, Observer {
            if(it!=null){
                total=it
            }else{
                total = 0
            }
            binding.tvTotal.text = "RM $total"
        })

        binding.btnPay.setOnClickListener {
            Toast.makeText(requireContext(),"- RM $total LMAO",Toast.LENGTH_SHORT).show()
        }

        cartViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            cart->adapter.setdata(cart)

        })

        return binding.root
    }
    private fun verifyList(list: MutableList<Cart>, data:List<Cart>):Int{
        var total =0
        for((count, item) in data.withIndex()){
            total += list[count].quantity*list[count].price
            if (item.quantity != list[count].quantity){
                if(list[count].quantity<=0){
                    cartViewModel.removeP(item.name)
                }else{
                    cartViewModel.updateQ(item.name,list[count].quantity)
                    adapter.notifyDataSetChanged()
                }
            }
        }
        return total

    }
}