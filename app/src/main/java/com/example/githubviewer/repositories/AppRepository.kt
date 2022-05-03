package com.example.githubviewer.repositories

import android.util.Log
import com.example.githubviewer.model.*
import com.example.githubviewer.repositories.api.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val apiClient: Api,
    private val client: OkHttpClient
) {

    suspend fun getRepositories(username: String): Flow<Response<List<Repo>>> {
        return flow {
            val response = apiClient.getRepositories(username)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Response.Success(it))
                }
            }
            emit(Response.Error(response.message(), response.code()))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getRepository(repoName: String, owner: String): Flow<Response<RepoDetails>> {
        return flow {
            val response = apiClient.getRepository(repoName, owner)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Response.Success(it))
                }
            }
            emit(Response.Error(response.message(), response.code()))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getRepositoryReadme(
        ownerName: String,
        repositoryName: String,
        branchName: String
    ): Flow<Response<String>> {
        return flow {
            val baseUrl = "https://raw.githubusercontent.com/"
            val fileName = "README.md"
            val requestUrl = "$baseUrl$ownerName/$repositoryName/$branchName/$fileName"
            val request = Request.Builder()
                .url(requestUrl)
                .build()
            val call = client.newCall(request)
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    response.body?.let {
                        emit(Response.Success(it.string()))
                    }
                }
                emit(Response.Error("No README file", response.code))
            } catch (error: Exception) {
                Log.d("Error", error.localizedMessage!!)
                emit(Response.Error(error.localizedMessage!!))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun signIn(): Flow<Response<UserInfo>> {
        return flow {
            val response = apiClient.signIn()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Response.Success(it))
                }
            }
            if (response.code() == 401) {
                emit(Response.Error("Wrong token", response.code()))
            }
            emit(Response.Error(response.message(), response.code()))
        }.flowOn(Dispatchers.IO)
    }
}