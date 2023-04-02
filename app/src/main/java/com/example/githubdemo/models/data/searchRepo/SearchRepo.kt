package com.example.githubdemo.models.data.searchRepo

data class SearchRepo(
    val total_count:Int,
    val incomplete_results:Boolean,
    val items: List<ItemRepo>
)
