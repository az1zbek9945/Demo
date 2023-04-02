package com.example.githubdemo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubdemo.domain.MainRepository
import com.example.githubdemo.models.data.accessToken.GetAccessTokenResponceData
import com.example.githubdemo.models.data.getUserProfileInfo.GetUserProfileInfo
import com.example.githubdemo.models.data.getUserRepo.GetUserRepositories
import com.example.githubdemo.models.data.result.ResultData
import com.example.githubdemo.models.data.searchRepo.ItemRepo
import com.example.githubdemo.models.data.searchUser.Items
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(private val repo: MainRepository): ViewModel() {

    val messageFlow = MutableSharedFlow<String>()
    val successFlow = MutableSharedFlow<String>()
    val errorFlow = MutableSharedFlow<Throwable>()

    val activeLoginFlow = MutableSharedFlow<GetAccessTokenResponceData>()
    val getUserProfileInfoFlow = MutableSharedFlow<GetUserProfileInfo>()
    val getUserRepositoriesFlow = MutableSharedFlow<List<GetUserRepositories>>()
    val searchUsersFlow = MutableSharedFlow<List<Items>>()
    val searchRepoFLow = MutableSharedFlow<List<ItemRepo>>()

    suspend fun getAccessToken() {
        repo.getAccessToken().onEach {
            when (it) {
                is ResultData.Success -> {
                    successFlow.emit(it.data.toString())
                }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)

    }

    suspend fun getUserProfileInfo() {
        repo.getUserProfileInfo().onEach {
            when (it) {
                is ResultData.Success -> {
                    getUserProfileInfoFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun getUserRepositories() {
        repo.getUserRepositories().onEach {
            when (it) {
                is ResultData.Success -> {
                    getUserRepositoriesFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun searchRepo(user: String){
        repo.getSearchUserRepositoriesByRepo(user).onEach {
            when (it) {
                is ResultData.Success -> {
                    it.data?.let { it1 -> searchRepoFLow.emit(it1) }
                }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun searchUser(user:String) {
        repo.getSearchUsersByUsername(user).onEach {
            when (it) {
                is ResultData.Success -> {
                    searchUsersFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

}