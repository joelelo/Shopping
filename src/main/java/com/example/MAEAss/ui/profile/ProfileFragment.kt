package com.example.mae.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.MAEAss.ui.data.Profile
import com.example.MAEAss.ui.profile.ProfileViewModelFactory
import com.example.mae.ui.Data.AppDatabase
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentProfileBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false)

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).profileDAO

        val viewModelFactory = ProfileViewModelFactory(dataSource,application)

        val profileViewModel=ViewModelProvider(this,viewModelFactory).get(ProfileViewModel::class.java)
        binding.profileViewModel=profileViewModel
        binding.lifecycleOwner=this
        val prof = Profile(0,1,"John Doe","Example Street")
        profileViewModel.insert(prof)

        profileViewModel.profile.observe(viewLifecycleOwner, Observer {
            profile->profileViewModel.updateProfile(profile)
            binding.imageView.setImageResource(when(profile.img){
                1->R.drawable.apple
                2->R.drawable.bnn
                else->R.drawable.chicken
            })
            binding.tvName.text = profile.name
            binding.tvAddress.text = profile.address

        })
        binding.btnEdit.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_editProfileFragment)
        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            findNavController().navigate(R.id.action_editProfileFragment_to_navigation_profile)
        }

        return binding.root
    }
}