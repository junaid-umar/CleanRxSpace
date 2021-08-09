package com.android.space.data.network.util

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class CustomDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            MockNetworkConfig.baseUrl -> {
                MockResponse().setResponseCode(MockNetworkConfig.status)
                    .setBody(MockWebServiceResponse.searchBlogResponse)
            }
            MockNetworkConfig.randomBaseUrl -> {
                MockResponse().setResponseCode(MockNetworkConfig.status)
                    .setBody(MockWebServiceResponse.searchBlogResponse)
            }
            else -> throw Exception("Unable to find ${request.path}")
        }
    }
}