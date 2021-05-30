package com.example.mae.ui.dashboard

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MAEAss.ui.data.Cart
import com.example.mae.ui.Data.Product
import com.example.mae.ui.Data.ProductDAO
import com.example.mae.ui.Data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : ViewModel() {
    val readAllData: LiveData<List<Product>>
    private val prodDAO: ProductDAO = AppDatabase.getInstance(application).productDAO

    init{
        readAllData=prodDAO.getAllProducts()
    }
    fun addProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO){
            prodDAO.insert(product)
        }
    }
    fun addToCart(cart: Cart){
        viewModelScope.launch(Dispatchers.IO){
            val check = prodDAO.checkExist(cart.name)
            if(check ==1){
                prodDAO.appendQCart(cart.name,cart.quantity)
            }else{
                prodDAO.addTC(cart)
            }
        }

    }
    fun searchDB(query:String): LiveData<List<Product>> {
        return prodDAO.search(query)
    }
}