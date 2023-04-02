package com.example.githubdemo.di

import com.example.githubdemo.models.local.LocalStorage
import okhttp3.Interceptor
import okhttp3.Response

class GitHubInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("Authorization","Bearer ${LocalStorage().token}").build()
        return chain.proceed(request)
    }


}