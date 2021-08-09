package com.android.space.data.network.util

import com.android.space.test.BuildConfig
import java.net.HttpURLConnection

object MockNetworkConfig {
    val baseUrl = BuildConfig.BASE_URL
    val randomBaseUrl = "https://unabletoresolvehost.com/"
    val status = HttpURLConnection.HTTP_OK
}