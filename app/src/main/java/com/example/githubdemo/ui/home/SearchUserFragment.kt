package com.example.githubdemo.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubclone.ui.adapters.GitHubUserRepoAdapter
import com.example.githubclone.ui.adapters.GithubUserSearchAdapter
import com.example.githubdemo.R
import com.example.githubdemo.databinding.SearchUserFragmentBinding
import com.example.githubdemo.presentation.MainViewModel
import com.example.githubdemo.utils.snackBar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchUserFragment:Fragment(R.layout.search_user_fragment) {
    private lateinit var binding: SearchUserFragmentBinding
    private lateinit var adapter: GithubUserSearchAdapter
    private val viewModel by viewModel<MainViewModel>()
    private val navArgs:SearchUserFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SearchUserFragmentBinding.bind(view)


        initObserves()

        binding.apply {
            userRecycler.adapter = adapter

            btnBack.findNavController().popBackStack()

            lifecycleScope.launchWhenResumed {
                viewModel.searchUser(navArgs.userName)
            }
        }
    }

    private fun initObserves(){
        viewModel.searchUsersFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            snackBar("qatelik")
        }.launchIn(lifecycleScope)
    }

}