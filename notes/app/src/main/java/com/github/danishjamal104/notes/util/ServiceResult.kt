package com.github.danishjamal104.notes.util

sealed class ServiceResult<out T> {
    data class Success<out T>(val data: T) : ServiceResult<T>()
    data class Error(val reason: String) : ServiceResult<Nothing>()
}
