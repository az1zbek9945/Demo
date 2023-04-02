package com.example.githubdemo.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.githubdemo.R
import com.example.githubdemo.databinding.LoginFragmentBinding
import com.example.githubdemo.models.local.LocalStorage
import com.example.githubdemo.presentation.MainViewModel
import com.example.githubdemo.utils.toast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.login_fragment) {
    private lateinit var binding: LoginFragmentBinding
    private val viewModel by viewModel<MainViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LoginFragmentBinding.bind(view)

        initObserves()

        binding.btnGoogleGithub.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/login/oauth/authorize?client_id=8f3cf5f09bd0c93a0528&scope=repo")
            )
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        val uri: Uri? = requireActivity().intent?.data
        if (uri != null) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                LocalStorage().code = code
                Log.d("ttttt","$code")
                //get Access Token zapros ketedi codedi alg'annan son'
                Toast.makeText(requireContext(), "Login success: $code", Toast.LENGTH_SHORT).show()
                lifecycleScope.launchWhenResumed {
                    viewModel.getAccessToken()
                }
            } else if ((uri.getQueryParameter("error")) != null) {
                Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
            initObserves()
        }
    }

    private fun initObserves(){
        viewModel.successFlow.onEach {
            LocalStorage().isLogin = true
            LocalStorage().token = it
            Log.d("ttt","$it")
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            toast("Qatelik bar")
        }.launchIn(lifecycleScope)
    }


}