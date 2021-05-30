package com.example.MAEAss.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.MAEAss.ui.data.Cart
import com.example.mae.ui.Data.AppDatabase
import com.example.mae.ui.dashboard.ProductViewModel
import com.example.mae.ui.dashboard.ProductViewModelFactory
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProductDisplayBinding

class ProductDisplayFragment : Fragment() {

    private val args by navArgs<ProductDisplayFragmentArgs>()
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->
                findNavController().navigate(R.id.action_productDisplayFragment_to_navigation_products)
        }
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentProductDisplayBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_display, container,false)
        val application = requireNotNull(this.activity).application

        val dataSource = AppDatabase.getInstance(application).productDAO
        val viewModelFactory = ProductViewModelFactory(dataSource, application)
        val productViewModel =
            ViewModelProvider(this,viewModelFactory).get(ProductViewModel::class.java)
        binding.productViewModel = productViewModel

        binding.lifecycleOwner=this

        binding.imgDisp.setImageResource(when(args.currentItem.prodImg){
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
        binding.tvNameDisplay.text = args.currentItem.prodName
        binding.tvPriceDisplay.text = args.currentItem.prodPrice.toString()
        binding.tvDescription.text = args.currentItem.prodDesc

        binding.btnAddTC.setOnClickListener {
            val cart = Cart(args.currentItem.prodName,args.currentItem.prodImg,args.currentItem.prodPrice,1)
            productViewModel.addToCart(cart)
            Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()
        }

        return binding.root


    }
}