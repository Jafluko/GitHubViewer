package com.example.githubviewer.di.interceptors

import com.example.githubviewer.model.KeyValueStorage
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val userStorage: KeyValueStorage) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = userStorage.authToken
        val request = chain.request().newBuilder()
            .header("Authorization", "token $token")
            .build()
        return chain.proceed(request)
    }
}