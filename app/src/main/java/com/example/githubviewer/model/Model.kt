package com.example.githubviewer.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

/*@Polymorphic
@Serializable(with = ModelSerializer::class)*/
sealed class Model {
    abstract val type: String
}
