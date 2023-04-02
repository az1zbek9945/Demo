package com.example.githubdemo.models.remote

import com.example.githubdemo.models.data.accessToken.GetAccessTokenResponceData
import com.example.githubdemo.models.data.getUserProfileInfo.GetUserProfileInfo
import com.example.githubdemo.models.data.getUserRepo.GetUserRepositories
import com.example.githubdemo.models.data.result.ResultData
import com.example.githubdemo.models.data.searchRepo.ItemRepo
import com.example.githubdemo.models.data.searchUser.Items
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface GitHubApi {


    @Headers("Accept: application/json")
    @POST("https://github.com/login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") client_id:String,
        @Field("client_secret") client_secret:String,
        @Field("code") code:String
    ):Response<ResultData<String>>

    @GET("/search/users?q")
    suspend fun getSearchUsersByUsername(@Query("q") user:String): Response<List<Items>>

    @GET("/search/repositories?q")
    suspend fun getSearchRepositoriesByRepositoriesName(@Query("q") repo: String): Response<List<ItemRepo>>



    @GET("/user")
    suspend fun getUserProfileInfo():Response<GetUserProfileInfo>


    @GET("/user/repos")
    suspend fun getUserRepositories():Response<List<GetUserRepositories>>





}