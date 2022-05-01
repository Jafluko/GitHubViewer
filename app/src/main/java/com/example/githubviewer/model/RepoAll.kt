package com.example.githubviewer.model

import kotlinx.serialization.Serializable

@Serializable
data class RepoAll(
    val repo: RepoDetails,
    val readme: String
)
