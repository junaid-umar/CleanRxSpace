package com.android.space.data.network.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.space.data.network.util.CustomDispatcher
import com.android.space.data.persistence.FakeDataUtil
import com.android.space.domain.model.Resource
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@HiltAndroidTest
class BlogRemoteDataSourceImplTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Inject
    lateinit var mockServer: MockWebServer


    @Inject
    lateinit var blogRemoteDataSource: BlogRemoteDataSourceImpl



    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    @Test
    fun searchBlogsWithValidQuery_returnsBlogs() {

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
    fun searchBlogs_NoNetwork_returnsTimeOutError() {


        val output = blogRemoteDataSource.searchBlogs(
            FakeDataUtil.Common.pageSize,
            FakeDataUtil.Common.page,
            FakeDataUtil.Common.querySearch
        ).blockingGet()

        assertThat(output).isInstanceOf(Resource.Error::class.java)
    }

}