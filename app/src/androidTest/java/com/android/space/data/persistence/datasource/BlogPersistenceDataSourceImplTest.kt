package com.android.space.data.persistence.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.space.data.persistence.FakeDataUtil
import com.android.space.data.persistence.dao.BlogDao
import com.android.space.data.persistence.database.AppDatabase
import com.android.space.data.persistence.entity.BlogEntityMapper
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlogPersistenceDataSourceImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var appDatabase: AppDatabase
    private lateinit var blogDao: BlogDao
    private lateinit var blogEntityMapper: BlogEntityMapper
    private lateinit var blogPersistenceDataSource: BlogPersistenceDataSourceImpl

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        blogDao = appDatabase.blogDao()
        blogEntityMapper = BlogEntityMapper()
        blogPersistenceDataSource = BlogPersistenceDataSourceImpl(
            blogDao, blogEntityMapper
        )
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