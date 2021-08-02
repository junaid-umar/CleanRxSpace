package com.android.space.domain.model
import com.android.space.domain.model.Resource.*

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String, val data: T? = null) : Resource<T>()
    data class Loading<T>(val data: T? = null) : Resource<T>()
}

fun <T, R> Resource<T>.transform(
    transform: ((value: T) -> R)
): Resource<R> = when (this) {
    is Success -> Success(transform.invoke(data))
    is Error -> Error(message, data?.let { transform.invoke(it) })
    is Loading -> Loading(data?.let { transform.invoke(it) })
}