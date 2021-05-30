package com.example.MAEAss.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.MAEAss.ui.data.Profile
import com.example.mae.ui.Data.AppDatabase
import com.example.mae.ui.home.ProfileViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->
                findNavController().navigate(R.id.action_editProfileFragment_to_navigation_profile)
        }
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding:FragmentEditProfileBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_edit_profile,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).profileDAO

        val viewModelFactory = ProfileViewModelFactory(dataSource,application)

        val profileViewModel= ViewModelProvider(this,viewModelFactory).get(ProfileViewModel::class.java)
        binding.profileViewModel=profileViewModel
        binding.lifecycleOwner=this


        profileViewModel.profile.observe(viewLifecycleOwner, Observer {
                profile->profile
            binding.etEditName.setText(profile.name)
            binding.etEditAddress.setText(profile.address)
        })
        binding.btnSave.setOnClickListener {
            val profile = Profile(profileViewModel.profile.value!!.id, profileViewModel.profile.value!!.img,binding.etEditName.text.toString(),binding.etEditAddress.text.toString())
            profileViewModel.updateProfile(profile)
            findNavController().navigate(EditProfileFragmentDirections.actionEditProfileFragmentToNavigationProfile())
        }

        return binding.root
    }
}