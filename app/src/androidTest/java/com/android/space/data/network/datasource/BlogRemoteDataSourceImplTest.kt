package com.android.space.data.network.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.space.data.network.api.BlogService
import com.android.space.data.network.util.CustomDispatcher
import com.android.space.data.network.util.MockNetworkConfig
import com.android.space.data.network.util.NetworkHelper
import com.android.space.data.persistence.FakeDataUtil
import com.android.space.domain.model.Resource
import com.google.common.truth.Truth.assertThat
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class BlogRemoteDataSourceImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockServer: MockWebServer
    private lateinit var blogService: BlogService
    private lateinit var blogRemoteDataSource: BlogRemoteDataSourceImpl


    @Before
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.dispatcher = CustomDispatcher()
        mockServer.start()


    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    @Test
    fun searchBlogsWithValidQuery_returnsBlogs() {
        getMockApi(MockNetworkConfig.baseUrl)


        val output = blogRemoteDataSource.searchBlogs(
            FakeDataUtil.Common.pageSize,
            FakeDataUtil.Common.page,
            FakeDataUtil.Common.querySearch
        ).blockingGet()


        assertThat(output).isInstanceOf(Resource.Success::class.java)
    }


    /**
     *MockRestAdapter should be used to stimulate Server errors
     */
    @Test
    fun searchBlogs_returnsHostError() {
        getMockApi(MockNetworkConfig.randomBaseUrl)


        val output = blogRemoteDataSource.searchBlogs(
            FakeDataUtil.Common.pageSize,
            FakeDataUtil.Common.page,
            FakeDataUtil.Common.querySearch
        ).blockingGet()

        assertThat(output).isInstanceOf(Resource.Error::class.java)
    }

    private fun getMockApi(baseUrl: String) {
        blogService = NetworkHelper.getMockApi(baseUrl)
        blogRemoteDataSource = BlogRemoteDataSourceImpl(blogService)
    }


}