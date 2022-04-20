package com.example.githubviewer.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val login: String? = "",
    val id: Int? = 0
)
