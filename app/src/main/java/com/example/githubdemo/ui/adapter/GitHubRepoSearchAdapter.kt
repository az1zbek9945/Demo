package com.example.githubclone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.githubdemo.R
import com.example.githubdemo.databinding.ItemListRepoBinding
import com.example.githubdemo.models.data.searchRepo.ItemRepo

class GitHubRepoSearchAdapter: ListAdapter<ItemRepo, GitHubRepoSearchAdapter.GitViewHolder>(diffCallBack){

    inner class GitViewHolder(private val binding: ItemListRepoBinding):
            ViewHolder(binding.root){
                fun bind() {
                    binding.apply {
                        val d = getItem(adapterPosition)
                        Glide.with(profileAvatar)
                            .load(d.owner.avatar_url)
                            .into(profileAvatar)
                        tvAppName.text = d.name
                        tvAppName.text = d.full_name
                    }
                }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false)
        val binding = ItemListRepoBinding.bind(v)
        return GitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {
        holder.bind()
    }



    private object diffCallBack: DiffUtil.ItemCallback<ItemRepo>() {
        override fun areItemsTheSame(oldItem: ItemRepo, newItem: ItemRepo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemRepo, newItem: ItemRepo): Boolean {
            return oldItem == newItem
        }

    }
}