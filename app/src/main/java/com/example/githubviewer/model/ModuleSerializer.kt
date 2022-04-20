package com.example.githubviewer.model

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/*
object ModelSerializer : JsonContentPolymorphicSerializer<Model>(Model::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Model> {
        return when (element.jsonObject["type"]?.jsonPrimitive?.content) {
            "User" -> UserInfo.serializer()
            else -> throw Exception("Unknown Module: key 'type' not found or does not matches any module type")
        }
    }
}*/
