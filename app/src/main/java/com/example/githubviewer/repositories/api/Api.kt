package com.example.githubviewer.repositories.api

import com.example.githubviewer.model.Repo
import com.example.githubviewer.model.RepoDetails
import com.example.githubviewer.model.UserInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("/users/{username}/repos")
    suspend fun getRepositories(
        @Path("username") name: String,
        @Query("per_page") amount: Int = 10
    ): List<Repo>

    @GET("/repos/{id}")
    suspend fun getRepository(@Path("id") repoId: String): RepoDetails

    @GET("/repos/{owner}/{repo}/readme")
    suspend fun getRepositoryReadme(
        @Path("owner") ownerName: String,
        @Path("repo") repositoryName: String,
        //branchName: String
    ): String

    @GET("/user")
    suspend fun signIn(): UserInfo
}