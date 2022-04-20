package com.example.githubviewer.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@SerialName("UserInfo")
data class UserInfo(
    val login: String? = "",
    val id: Int? = 0
)
