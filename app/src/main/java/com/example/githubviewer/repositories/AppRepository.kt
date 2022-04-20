package com.example.githubviewer.repositories

import com.example.githubviewer.model.Repo
import com.example.githubviewer.model.RepoDetails
import com.example.githubviewer.model.User
import com.example.githubviewer.model.UserInfo
import com.example.githubviewer.repositories.api.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private val apiClient: Api) {

    suspend fun getRepositories(username: String): Flow<List<Repo>> {
        return apiClient.getRepositories(username)
            .flowOn(Dispatchers.IO)
    }

    suspend fun getRepository(repoId: String): Flow<RepoDetails> {
        return apiClient.getRepository(repoId)
            .flowOn(Dispatchers.IO)
    }

    suspend fun getRepositoryReadme(
        ownerName: String,
        repositoryName: String,
        branchName: String
    ): Flow<String> {
        return apiClient.getRepositoryReadme(ownerName, repositoryName, branchName)
            .flowOn(Dispatchers.IO)
    }

    suspend fun signIn(/*token: String*/): Flow<UserInfo> {
        return apiClient.signIn(/*token*/)
            .flowOn(Dispatchers.IO)
            
    }
}