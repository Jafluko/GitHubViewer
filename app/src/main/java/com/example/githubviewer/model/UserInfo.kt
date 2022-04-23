package com.example.githubviewer.model

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
@SerialName("UserInfo")
data class UserInfo(
    val login: String? = "",
    val id: Int? = 0
)
