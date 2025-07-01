package com.reringuy.sync.utils

sealed class ApiResponse<T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    class Error<T>(val ex: Throwable) : ApiResponse<T>()
    companion object {
        fun <T> success(data: T) = Success(data)
        fun <T> error(ex: Throwable): ApiResponse<T> = Error(ex)
    }
}