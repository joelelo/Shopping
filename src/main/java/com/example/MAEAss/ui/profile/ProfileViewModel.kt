package com.example.mae.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MAEAss.ui.data.Profile
import com.example.MAEAss.ui.data.ProfileDAO
import com.example.mae.ui.Data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : ViewModel() {
    var profile: LiveData<Profile>
    private val profileDAO:ProfileDAO = AppDatabase.getInstance(application).profileDAO

    init {
        profile =profileDAO.getProfile()
    }
    fun updateProfile(profile: Profile){
        viewModelScope.launch(Dispatchers.IO){
            profileDAO.update(profile)
        }
    }
    fun insert(profile: Profile){
        viewModelScope.launch(Dispatchers.IO){
            profileDAO.insert(profile)
        }
    }
}