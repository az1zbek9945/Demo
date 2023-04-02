package com.example.githubdemo.models.data.searchUser


data class SearchUsers(
    val total_count :Int,
    val incomplete_results :Boolean,
    val items :List<Items>
)
