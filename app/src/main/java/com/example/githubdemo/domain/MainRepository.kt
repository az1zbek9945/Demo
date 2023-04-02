package com.example.githubdemo.domain

import com.example.githubdemo.models.data.result.ResultData
import com.example.githubdemo.models.local.LocalStorage
import com.example.githubdemo.models.remote.GitHubApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MainRepository(private val api: GitHubApi) {

    suspend fun getSearchUsersByUsername(user: String) = flow {
        val response = api.getSearchUsersByUsername(user)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }

    suspend fun getSearchUserRepositoriesByRepo(name: String) = flow {
        val response = api.getSearchRepositoriesByRepositoriesName(name)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }

    suspend fun getUserProfileInfo() = flow {
        val response = api.getUserProfileInfo()
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch { emit(ResultData.Error(it)) }

    suspend fun getUserRepositories() = flow {
        val response = api.getUserRepositories()
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    suspend fun getAccessToken() = flow {
        val response = api.getAccessToken(
            "523127737a2f46951a62",
            "f3453d3d8a410df42b242b509fc4ea0867babea0",
            LocalStorage().code
        )
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }


}