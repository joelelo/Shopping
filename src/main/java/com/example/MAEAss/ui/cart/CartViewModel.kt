package com.example.mae.ui.notifications

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MAEAss.ui.data.Cart
import com.example.mae.ui.Data.AppDatabase
import com.example.mae.ui.Data.ProductDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : ViewModel() {
    val readAllData: LiveData<List<Cart>>
    private val prodDAO: ProductDAO = AppDatabase.getInstance(application).productDAO

    init {
        readAllData=prodDAO.getCart()
    }
    fun updateQ(name:String, quantity:Int){
        viewModelScope.launch(Dispatchers.IO){
            prodDAO.updateQCart(name,quantity)
        }
    }
    fun removeP(name: String){
        viewModelScope.launch(Dispatchers.IO){
            prodDAO.removeFC(name)
        }
    }
    fun getTotal():LiveData<Int>{
        return prodDAO.getTotal()
    }
}