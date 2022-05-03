package com.example.githubviewer.repositories.api

import com.example.githubviewer.model.Repo
import com.example.githubviewer.model.RepoDetails
import com.example.githubviewer.model.UserInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("/users/{username}/repos")
    suspend fun getRepositories(
        @Path("username") name: String,
        @Query("per_page") amount: Int = 10
    ): Response<List<Repo>>

    @GET("/repos/{owner}/{repoName}")
    suspend fun getRepository(
        @Path("repoName") repoName: String,
        @Path("owner") owner: String
    ): Response<RepoDetails>

    @GET("/user")
    suspend fun signIn(): Response<UserInfo>
}