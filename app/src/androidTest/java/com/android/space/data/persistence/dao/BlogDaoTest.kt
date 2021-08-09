package com.android.space.data.persistence.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.space.data.persistence.FakeDataUtil
import com.android.space.data.persistence.FakeDataUtil.Common.page
import com.android.space.data.persistence.FakeDataUtil.Common.pageSize
import com.android.space.data.persistence.FakeDataUtil.Common.querySearch
import com.android.space.data.persistence.database.AppDatabase
import com.android.space.data.persistence.entity.BlogEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
class BlogDaoTest {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var database: AppDatabase

    @Inject
    lateinit var dao: BlogDao

    @Before
    fun setUp() {
        hiltRule.inject()
        dao = database.blogDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertBlog_success() {
        val input = FakeDataUtil.BlogEntity.blog1
        dao.insertBlog(input).blockingAwait()

        dao.searchBlog(input.id)
            .test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertComplete()
            .assertValue {
                it.compareTo(input)
            }
    }

    @Test
    fun insertBlogs_success() {
        val input = FakeDataUtil.BlogEntity.getFakeBlogEntities()

        dao.insertBlogs(input).blockingAwait()

        dao.searchBlogs(pageSize, page, querySearch)
            .test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue {
                it.size == input.size
                        && it[0].compareTo(input[0])
                        && it[1].compareTo(input[1])
            }
    }

    @Test
    fun searchBlogs_returnsBlog() {
        val input = FakeDataUtil.BlogEntity.getFakeBlogEntities()

        dao.insertBlogs(input).blockingAwait()

        dao.searchBlogs(pageSize, page, querySearch)
            .test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue {
                it.all {
                    it.title.contains(querySearch)
                }
            }
    }

    @Test
    fun searchRandomBlogs_returnsEmpty() {
        val input = FakeDataUtil.BlogEntity.getFakeBlogEntities()

        dao.insertBlogs(input).blockingAwait()

        dao.searchBlogs(pageSize, page, "Random")
            .test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue {
                it.size == 0
            }
    }


    private fun BlogEntity.compareTo(entity: BlogEntity): Boolean =
        this.run {
            id == entity.id && title == entity.title
                    && summary == entity.summary && imgUrl ==
                    entity.imgUrl && publishedAt == entity.publishedAt
        }


}