package com.example.githubviewer.model

import kotlinx.serialization.Serializable

@Serializable
data class Repo(
    val id: Int? = 0,
    val name: String? = "",
    val description: String? = "",
    val language: String? = ""
)
