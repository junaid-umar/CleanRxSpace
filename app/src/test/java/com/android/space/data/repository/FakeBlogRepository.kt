package com.android.space.data.repository

import com.android.space.FakeDataUtil
import com.android.space.data.network.datasource.FakeBlogRemoteDataSource
import com.android.space.data.persistence.datasource.FakeBlogPersistenceDataSource
import com.android.space.domain.model.Blog
import com.android.space.domain.model.Resource
import com.android.space.domain.repository.BlogRepository
import com.google.common.truth.Truth.assertThat
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FakeBlogRepository {

    private lateinit var blogRepository: BlogRepository

    private lateinit var blogPersistenceDataSource: FakeBlogPersistenceDataSource
    private lateinit var blogRemoteDataSource: FakeBlogRemoteDataSource


    @BeforeEach
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        blogPersistenceDataSource = FakeBlogPersistenceDataSource()
        blogRemoteDataSource = FakeBlogRemoteDataSource()

        blogRepository = BlogRepositoryImpl(
            blogPersistenceDataSource,
            blogRemoteDataSource
        )
    }


    @Test
    fun `search blogs with valid cache and network error return network error`() {

        val blogList = FakeDataUtil.Domain.getFakeBlogList()
        blogPersistenceDataSource.insertBlogs(blogList)
        blogRemoteDataSource.fake_setBehaviour(FakeBlogRemoteDataSource.Behaviour.SHOULD_RETURN_ERROR)

        val testObserver: TestObserver<Resource<List<Blog>>> = TestObserver()
        blogRepository.searchBlogs(
            FakeDataUtil.Common.pageSize,
            FakeDataUtil.Common.page,
            FakeDataUtil.Common.querySearch,
        ).subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(3)


        val result = testObserver.values()

        assertThat(result.get(0)).isEqualTo(Resource.Loading(null))
        assertThat(result.get(1)).isEqualTo(Resource.Loading(blogList))
        assertThat(result.get(2)).isEqualTo(Resource.Error<Resource<List<Blog>>>("Error"))

    }

    @Test
    fun `search blogs with valid cache and network return success`() {

        val blogList = FakeDataUtil.Domain.getFakeBlogList()
        blogPersistenceDataSource.insertBlogs(blogList)
        blogRemoteDataSource.fake_setBehaviour(FakeBlogRemoteDataSource.Behaviour.SHOULD_RETURN_SUCCESS)

        val testObserver: TestObserver<Resource<List<Blog>>> = TestObserver()
        blogRepository.searchBlogs(
            FakeDataUtil.Common.pageSize,
            FakeDataUtil.Common.page,
            FakeDataUtil.Common.querySearch,
        ).subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(3)


        val result = testObserver.values()

        assertThat(result.get(0)).isEqualTo(Resource.Loading(null))
        assertThat(result.get(1)).isEqualTo(Resource.Loading(blogList))
        assertThat(result.get(2)).isEqualTo(Resource.Success<List<Blog>>(blogList))

    }

    @Test
    fun `search blogs with empty cache and network error return network error`() {

        blogRemoteDataSource.fake_setBehaviour(FakeBlogRemoteDataSource.Behaviour.SHOULD_RETURN_ERROR)

        val testObserver: TestObserver<Resource<List<Blog>>> = TestObserver()
        blogRepository.searchBlogs(
            FakeDataUtil.Common.pageSize,
            FakeDataUtil.Common.page,
            FakeDataUtil.Common.querySearch,
        ).subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(2)


        val result = testObserver.values()

        assertThat(result.get(0)).isEqualTo(Resource.Loading(null))
        assertThat(result.get(1)).isEqualTo(Resource.Error<List<Blog>>("Error"))

    }
}