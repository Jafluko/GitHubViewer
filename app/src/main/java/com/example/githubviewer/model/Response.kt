package com.example.githubviewer.model

sealed class Response<T> (val data: T? = null, val message: String? = null, val code: Int? = null) {
    class Success<T> (data: T) : Response<T>(data)
    class Error<T>(message: String, code: Int? = null) : Response<T>(message = message, code = code)

    fun unitResponse(): Response<Unit> {
        return when (this) {
            is Success -> Success(Unit)
            is Error -> Error(message = message!!, code = code)
        }
    }
}
