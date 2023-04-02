package com.example.githubdemo.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubdemo.MainActivity
import com.example.githubdemo.R
import com.example.githubdemo.databinding.SplashFragmentBinding
import com.example.githubdemo.models.local.LocalStorage
import kotlinx.coroutines.delay

class SplashFragment:Fragment(R.layout.splash_fragment) {
    private lateinit var binding: SplashFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SplashFragmentBinding.bind(view)


        lifecycleScope.launchWhenResumed {
            delay(1000)
            if (LocalStorage().isLogin){
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToHomeFragment()
                )
            }else {
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                )
            }

        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).visibilityBottom(View.GONE)
    }
}