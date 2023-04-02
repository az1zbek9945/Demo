package com.example.githubdemo.ui.profileFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.githubdemo.R
import com.example.githubdemo.databinding.ProfileFragmentBinding
import com.example.githubdemo.presentation.MainViewModel
import com.example.githubdemo.ui.adapter.GitHubProfileAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private lateinit var binding: ProfileFragmentBinding
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var adapter: GitHubProfileAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProfileFragmentBinding.bind(view)

        binding.apply {
            adapter = GitHubProfileAdapter()
            recyclerProfile.adapter = adapter
            lifecycleScope.launchWhenResumed {
                viewModel.getUserRepositories()
                viewModel.getUserProfileInfo()
            }


        }

        initObserves()


    }

    private fun initObserves() {
        viewModel.getUserProfileInfoFlow.onEach {
            binding.apply {
                Glide.with(profileAvatarBig)
                    .load(it.avatar_url)
                    .into(profileAvatarBig)
                tvLoginName.text = it.login
                tvFollowers.text = "{it.followers} followers"
                tvFollowersCount.text = it.public_repos.toString()
            }
        }.launchIn(lifecycleScope)

        viewModel.getUserRepositoriesFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

    }
}