package com.example.githubdemo.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.githubclone.ui.adapters.GitHubRepoSearchAdapter
import com.example.githubdemo.R
import com.example.githubdemo.databinding.SearchfragmentBinding
import com.example.githubdemo.presentation.MainViewModel
import com.example.githubdemo.utils.snackBar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment:Fragment(R.layout.searchfragment) {
    private lateinit var binding: SearchfragmentBinding
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var adapter: GitHubRepoSearchAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SearchfragmentBinding.bind(view)

        initObserves()

        binding.apply {
            userCons.visibility = View.VISIBLE
            repoCons.visibility = View.VISIBLE


            btnBack.findNavController().popBackStack()

            searchView.addTextChangedListener {
                val text = searchView.text.toString()

                tvRepo.text = getString(R.string.repoText, text)
                tvUser.text = getString(R.string.peopleText, text)


                userCons.setOnClickListener {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToSearchUserFragment(text)
                    )
                }

                repoCons.setOnClickListener {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToSearchRepoFragment(text)
                    )
                }
            }

        }
    }

    fun initObserves(){
        viewModel.searchRepoFLow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            snackBar(it)
        }.launchIn(lifecycleScope)
    }
}