package com.example.githubviewer.model

import kotlinx.serialization.Serializable

@Serializable
data class ReadMe(
    val content: String? = null
)
