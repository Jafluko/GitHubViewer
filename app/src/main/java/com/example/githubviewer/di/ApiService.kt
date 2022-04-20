package com.example.githubviewer.di

import com.example.githubviewer.di.interceptors.AcceptInterceptor
import com.example.githubviewer.di.interceptors.AuthInterceptor
import com.example.githubviewer.model.KeyValueStorage
import com.example.githubviewer.repositories.api.Api
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@ExperimentalSerializationApi
@InstallIn(SingletonComponent::class)
class ApiService {

    private val contentType = "application/json".toMediaType()

    @Singleton
    @Provides
    fun getAuthInterceptor(userStorage: KeyValueStorage): Interceptor = AuthInterceptor(userStorage)

    @Singleton
    @Provides
    fun getAcceptInterceptor(): Interceptor = AcceptInterceptor()

    @Singleton
    @Provides
    fun getJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
            coerceInputValues = true
        }
    }

    @Provides
    @Singleton
    fun getApiClient(okHttpClient: OkHttpClient, json: Json): Api = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
        .create(Api::class.java)

    @Provides
    fun getOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        acceptInterceptor: AcceptInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(acceptInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    fun getHttpLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}