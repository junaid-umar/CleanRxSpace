package com.android.space.data.util

import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkUtil {

    private const val genericNetworkError = "An error occurred getting data from server."

    fun getNetworkErrorMessage(t: Throwable) = when (t) {
        is IOException -> "Network not available."
        is SocketTimeoutException -> "Request timed out. Try again."
        is UnknownHostException -> "Unable to connect to server."
        else -> genericNetworkError
    }

    fun getErrorMessage(httpCode: Int) = when (httpCode) {
        // User unauthorised error
        401 -> "You have been unauthorized."
        // Time out error
        408 -> "Request timed out. Try again."
        // Internal server error
        500 -> "A server error occurred."
        // Any other error executing the API
        else -> genericNetworkError
    }
}