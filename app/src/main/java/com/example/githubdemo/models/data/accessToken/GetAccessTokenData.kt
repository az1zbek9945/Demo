package com.example.githubdemo.models.data.accessToken

data class GetAccessTokenData(
    val client_id:String,
    val client_secret:String,
    val code:String
)