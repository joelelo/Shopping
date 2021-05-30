package com.example.mae.ui.dashboard

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mae.ui.Data.ProductDAO
import java.lang.IllegalArgumentException

class ProductViewModelFactory (
        private val dataSource: ProductDAO,
        private val application: Application):ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductViewModel::class.java)){
            return ProductViewModel( application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
