package com.chilate.data.core

/**
 * Created by Kevel on 4/21/2023.
 */

sealed class RepositoryResponse<out T> {
    data class Success<T>(val data: T): RepositoryResponse<T>()
    data class Error(val code: Int, val message: String, val throwable: Exception? = null): RepositoryResponse<Nothing>()
}