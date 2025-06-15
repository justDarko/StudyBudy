package com.solo.core

sealed class CustomResult<out T> {
    data class Success<out T>(val data: T) : CustomResult<T>()
    data class Failure(val message: String, val throwable: Throwable? = null) :
        CustomResult<Nothing>()
}
