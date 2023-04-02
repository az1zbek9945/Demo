package com.example.githubdemo.models.data.accessToken


data class GetAccessTokenResponceData(
    val access_token: String,
    val scope: String,
    val token_type: String
)
