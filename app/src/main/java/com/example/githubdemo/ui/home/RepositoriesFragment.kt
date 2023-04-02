package com.example.githubdemo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubclone.ui.adapters.GitHubUserRepoAdapter
import com.example.githubdemo.MainActivity
import com.example.githubdemo.R
import com.example.githubdemo.databinding.RepositoriesFragmentBinding
import com.example.githubdemo.presentation.MainViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoriesFragment : Fragment(R.layout.repositories_fragment) {
    private lateinit var binding: RepositoriesFragmentBinding
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var adapter : GitHubUserRepoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RepositoriesFragmentBinding.bind(view)

        adapter = GitHubUserRepoAdapter(mutableListOf())
        binding.recyclerView.adapter = adapter

        initObserves()

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }


        binding.apply {
            lifecycleScope.launchWhenResumed {
                viewModel.getUserRepositories()
            }
        }


    }


    private fun initObserves() {
        viewModel.getUserRepositoriesFlow.onEach {
            Log.d("sssss","")
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).visibilityBottom(View.GONE)
    }


}