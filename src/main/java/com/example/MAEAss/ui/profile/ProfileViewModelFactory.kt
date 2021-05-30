package com.example.MAEAss.ui.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.MAEAss.ui.data.ProfileDAO
import com.example.mae.ui.home.ProfileViewModel

class ProfileViewModelFactory (
    private val dataSource:ProfileDAO,
    private val application: Application): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel( application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    }
