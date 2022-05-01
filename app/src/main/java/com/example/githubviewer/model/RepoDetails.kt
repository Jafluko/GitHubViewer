package com.example.githubviewer.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoDetails(
    val name: String? = "",
    @SerialName("html_url") val htmlUrl: String? = "",
    @SerialName("forks_count") val forks: Int? = 0,
    @SerialName("stargazers_count") val stargazers: Int? = 0,
    @SerialName("watchers_count") val watchers: Int? = 0,
    @SerialName("default_branch") val branch: String? = null,
    val license: License? = null
) {
    @Serializable
    data class License(
        val name: String? = ""
    )
}