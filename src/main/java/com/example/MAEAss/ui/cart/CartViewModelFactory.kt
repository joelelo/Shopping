package com.example.MAEAss.ui.cart

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mae.ui.Data.ProductDAO
import com.example.mae.ui.home.ProfileViewModel
import com.example.mae.ui.notifications.CartViewModel

class CartViewModelFactory (
        private val dataSource: ProductDAO,
        private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}