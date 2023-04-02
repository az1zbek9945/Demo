package com.example.githubdemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.githubdemo.R
import com.example.githubdemo.databinding.ItemListPopularBinding
import com.example.githubdemo.models.data.getUserRepo.GetUserRepositories

class GitHubProfileAdapter:ListAdapter<GetUserRepositories,GitHubProfileAdapter.RepoProfileViewHolder>(diffCallback) {

    inner class RepoProfileViewHolder(private val binding: ItemListPopularBinding):
        ViewHolder(binding.root){
            fun bind(){
                val d = getItem(absoluteAdapterPosition)
                binding.apply {
                    Glide.with(avatarSmall)
                        .load(d.owner.avatar_url)
                        .into(avatarSmall)
                    tvLogin.text = d.owner.login
                    tvAppName.text = d.name
                    countPopular.text = d.stargazers_count.toString()

                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoProfileViewHolder {
        return RepoProfileViewHolder(
            ItemListPopularBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_list_popular,parent,false
                )
            )
        )
    }

    override fun onBindViewHolder(holder: RepoProfileViewHolder, position: Int) {
        holder.bind()
    }

    private object diffCallback:DiffUtil.ItemCallback<GetUserRepositories>(){
        override fun areItemsTheSame(
            oldItem: GetUserRepositories,
            newItem: GetUserRepositories
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: GetUserRepositories,
            newItem: GetUserRepositories
        ): Boolean {
            return oldItem == newItem
        }

    }
}