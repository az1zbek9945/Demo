package com.example.githubdemo.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.githubdemo.MainActivity
import com.example.githubdemo.R
import com.example.githubdemo.databinding.HomeFragmentBinding

class HomeFragment:Fragment(R.layout.home_fragment) {

    private lateinit var binding: HomeFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeFragmentBinding.bind(view)


        binding.apply {

            llRepositories.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToRepositoriesFragment()
                )
            }

            sv.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToSearchFragment()
                )
            }
        }
    }


    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).visibilityBottom(View.VISIBLE)
    }
}