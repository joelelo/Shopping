package com.example.mae.ui.dashboard

import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProductsBinding
import com.example.mae.ui.Data.AppDatabase
import com.example.mae.ui.Data.Product
import com.example.mae.ui.Data.ProductDAO

class ProductFragment : Fragment(), SearchView.OnQueryTextListener{

    lateinit var binding:FragmentProductsBinding


    lateinit var application: Application

    lateinit var dataSource:ProductDAO
    lateinit var viewModelFactory:ProductViewModelFactory


    lateinit var productViewModel:ProductViewModel
    lateinit var adapter:ProductAdapter
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        application = requireNotNull(this.activity).application
        dataSource = AppDatabase.getInstance(application).productDAO
        viewModelFactory = ProductViewModelFactory(dataSource, application)
        productViewModel =
                ViewModelProvider(this,viewModelFactory).get(ProductViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_products, container, false)

        binding.productViewModel = productViewModel

        binding.lifecycleOwner=this

        adapter = ProductAdapter()

        binding.recyclerviewp.adapter = adapter

        binding.recyclerviewp.layoutManager = GridLayoutManager(requireContext(),2)


//        val callback = OnBackPressedDispatcher().addCallback(this){
//            handleOnBackPressed()
//            findNavController().navigate(R.id.action_productDisplayFragment_to_navigation_products)
//        }
//        callback.isEnabled



        productViewModel.readAllData.observe(viewLifecycleOwner, Observer { product->
            adapter.setdata(product)
            if (product.isEmpty()){
                val product = Product(0,1,"Apple",100,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                val product1 = Product(0,2,"Banana",100,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                val product2 = Product(0,3,"Chicken",100,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                val product3 = Product(0,4,"Shampoo",100,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                val product4 = Product(0,5,"Detergent",100,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                val product5 = Product(0,6,"Guava Juice",100,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                val product6 = Product(0,7,"Apple Juice",100,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                val product7 = Product(0,8,"Milk",100,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")

                productViewModel.addProduct(product)
                productViewModel.addProduct(product1)
                productViewModel.addProduct(product2)
                productViewModel.addProduct(product3)
                productViewModel.addProduct(product4)
                productViewModel.addProduct(product5)
                productViewModel.addProduct(product6)
                productViewModel.addProduct(product7)
            }

        }

        )



        return binding.root
    }

    override fun setHasOptionsMenu(hasMenu: Boolean) {
        super.setHasOptionsMenu(hasMenu)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.search,menu)
        val item = menu?.findItem(R.id.search_bar)
        val searchView= item.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query!=null){
            searchDB(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query!=null){
            searchDB(query)
        }
        return true
    }

    private fun searchDB(query:String){
        val searchQ = "%$query%"
        productViewModel.searchDB(searchQ).observe(viewLifecycleOwner, Observer {
            adapter.setdata(it)
        })
    }
}