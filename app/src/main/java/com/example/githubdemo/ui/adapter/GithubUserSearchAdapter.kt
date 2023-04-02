package com.example.githubclone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.githubdemo.R
import com.example.githubdemo.databinding.ItemListUserBinding
import com.example.githubdemo.models.data.searchUser.Items

class GithubUserSearchAdapter:ListAdapter<Items,GithubUserSearchAdapter.GitViewHolder>(diffCallback){

    inner class GitViewHolder(private val binding: ItemListUserBinding):
        ViewHolder(binding.root){
            fun bind() {
                binding.apply {
                    val d = getItem(adapterPosition)
                    Glide.with(profileAvatar)
                        .load(d.avatar_url)
                        .into(profileAvatar)
                    tvLogin.text = d.login

                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list_user,parent,false)
        val binding = ItemListUserBinding.bind(v)
        return GitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {
        holder.bind()
    }

    private object diffCallback:DiffUtil.ItemCallback<Items>(){
        override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem == newItem
        }

    }
}