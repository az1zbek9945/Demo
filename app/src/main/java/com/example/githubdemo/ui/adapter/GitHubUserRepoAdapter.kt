package com.example.githubclone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.githubdemo.R
import com.example.githubdemo.databinding.ItemListRepoUserBinding
import com.example.githubdemo.models.data.getUserRepo.GetUserRepositories

class GitHubUserRepoAdapter(mutableListOf: MutableList<Any>) :
    ListAdapter<GetUserRepositories,GitHubUserRepoAdapter.GitRepoViewHolder>(diffCallback) {

    inner class GitRepoViewHolder(private var binding: ItemListRepoUserBinding)
        :ViewHolder(binding.root){
            fun bind() {
                val d = getItem(absoluteAdapterPosition)
                binding.apply {
                    Glide.with(profileAvatar)
                        .load(d.owner.avatar_url)
                        .into(profileAvatar)
                    tvLogin.text =d.owner.login
                    tvAppName.text = d.name
                }
            }
        }

    private object diffCallback:DiffUtil.ItemCallback<GetUserRepositories>(){
        override fun areItemsTheSame(
            oldItem: GetUserRepositories,
            newItem: GetUserRepositories
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GetUserRepositories,
            newItem: GetUserRepositories
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list_repo_user,parent,false)
        val binding = ItemListRepoUserBinding.bind(v)
        return GitRepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GitRepoViewHolder, position: Int) {
        holder.bind()
    }
}