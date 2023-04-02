package com.example.githubdemo.di

import com.example.githubdemo.domain.MainRepository
import com.example.githubdemo.models.remote.GitHubApi
import com.example.githubdemo.presentation.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<MainRepository> {
        MainRepository(api = get())
    }

    single<GitHubApi> {
        provideApi(retrofit = get())
    }
}

val remoteModule = module {
    single<GitHubInterceptor> {
        GitHubInterceptor()
    }

    single<Retrofit> {
        val httpLoginInterceptor = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
        )

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoginInterceptor)
            .addInterceptor(GitHubInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com")
            .client(client)
            .build()
        retrofit
    }
}

fun provideApi(retrofit: Retrofit): GitHubApi {
    return retrofit.create(GitHubApi::class.java)
}

val viewModelModule = module {
    viewModel<MainViewModel> {
        MainViewModel(repo = get())
    }
}