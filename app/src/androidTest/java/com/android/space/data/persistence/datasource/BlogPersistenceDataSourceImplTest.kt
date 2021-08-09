package com.android.space.data.persistence.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.space.data.persistence.FakeDataUtil
import com.android.space.data.persistence.database.AppDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class BlogPersistenceDataSourceImplTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var appDatabase: AppDatabase

    @Inject
    lateinit var blogPersistenceDataSource: BlogPersistenceDataSourceImpl

    @Before
    fun setUp() {
        hiltRule.inject()

    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun insertBlog_searchBlog() {

        val input = FakeDataUtil.Domain.blog1

        blogPersistenceDataSource.insertBlog(input)
            .andThen(
                blogPersistenceDataSource.searchBlogs(
                    FakeDataUtil.Common.pageSize,
                    FakeDataUtil.Common.page, FakeDataUtil.Common.querySearch
                )
            )
            .test()
            .assertValue {
                it.size == 1
            }

    }

    @Test
    fun insertBlogs_searchBlogs() {
        val input = FakeDataUtil.Domain.getFakeBlogList()
        blogPersistenceDataSource.insertBlogs(input)
            .andThen(
                blogPersistenceDataSource.searchBlogs(
                    FakeDataUtil.Common.pageSize,
                    FakeDataUtil.Common.page, FakeDataUtil.Common.querySearch
                )
            )
            .test()
            .assertValue {
                it.size == input.size
            }

    }

    @Test
    fun searchBlogs() {
        val input = FakeDataUtil.Domain.getFakeBlogList()

        blogPersistenceDataSource.insertBlogs(input).blockingAwait()

        blogPersistenceDataSource.searchBlogs(
            FakeDataUtil.Common.pageSize,
            FakeDataUtil.Common.page,
            FakeDataUtil.Common.querySearch
        )
            .test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue {
                it.all {
                    it.title.contains(FakeDataUtil.Common.querySearch)
                }
            }
    }

}