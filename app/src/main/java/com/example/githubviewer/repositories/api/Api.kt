package com.example.githubviewer.repositories.api

import com.example.githubviewer.model.Repo
import com.example.githubviewer.model.RepoDetails
import com.example.githubviewer.model.User
import com.example.githubviewer.model.UserInfo
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/users/{username}/repos")
    suspend fun getRepositories(@Path("username") name: String): Flow<List<Repo>>

    @GET("/repos/{id}")
    suspend fun getRepository(@Path("id") repoId: String): Flow<RepoDetails>

    @GET("/repos/{owner}/{repo}/readme")
    suspend fun getRepositoryReadme(
        @Path("owner") ownerName: String,
        @Path("repo") repositoryName: String,
        branchName: String
    ): Flow<String>

    @GET("/user")
    suspend fun signIn(/*@Header("Authorization") token: String*/): Flow<UserInfo>
}