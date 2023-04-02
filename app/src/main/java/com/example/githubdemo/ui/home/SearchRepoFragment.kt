package com.example.githubdemo.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubclone.ui.adapters.GitHubRepoSearchAdapter
import com.example.githubdemo.R
import com.example.githubdemo.databinding.SearchRepoFragmentBinding
import com.example.githubdemo.presentation.MainViewModel
import com.example.githubdemo.utils.snackBar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchRepoFragment:Fragment(R.layout.search_repo_fragment) {
    private lateinit var binding: SearchRepoFragmentBinding
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var adapter : GitHubRepoSearchAdapter
    private val navArgs: SearchRepoFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SearchRepoFragmentBinding.bind(view)

        initObserves()

        binding.apply {
            repoRecycler.adapter = adapter


            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            lifecycleScope.launchWhenResumed {
                viewModel.searchRepo(navArgs.repoName)
            }
        }
    }

    private fun initObserves(){
        viewModel.searchRepoFLow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            snackBar("qate")
        }.launchIn(lifecycleScope)
    }
}