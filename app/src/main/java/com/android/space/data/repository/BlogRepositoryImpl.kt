package com.android.space.data.repository

import com.android.space.data.network.datasource.BlogRemoteDataSource
import com.android.space.data.persistence.datasource.BlogPersistenceDataSource
import com.android.space.domain.model.Blog
import com.android.space.domain.model.Resource
import com.android.space.domain.repository.BlogRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class BlogRepositoryImpl
@Inject
constructor(
    private val blogPersistenceDataSource: BlogPersistenceDataSource,
    private val blogRemoteDataSource: BlogRemoteDataSource
) : BlogRepository {
    override fun searchBlogs(
        pageSize: Int,
        page: Int,
        query: String
    ): Observable<Resource<List<Blog>>> {
        return getBlogsFromCache(pageSize, page, query)
            .publish { o ->
                Observable.concatEager(
                    arrayListOf(
                        o.filter { it.isNotEmpty() }.map { Resource.Loading(it) },
                        getBlogsFromNetwork(pageSize, page, query)
                    )
                )
            }.startWithItem(Resource.Loading()).subscribeOn(Schedulers.io())

    }

    private fun getBlogsFromCache(
        pageSize: Int,
        page: Int,
        query: String
    ) =
        blogPersistenceDataSource
            .searchBlogs(pageSize, page, query)


    private fun getBlogsFromNetwork(
        pageSize: Int,
        page: Int,
        query: String
    ) =
        blogRemoteDataSource.searchBlogs(pageSize, page, query)
            .doOnSuccess { updateBlogsCache(it) }.toObservable()

    private fun updateBlogsCache(blogs: Resource<List<Blog>>) {
        Single.fromCallable {
            if (blogs is Resource.Success)
                blogPersistenceDataSource.insertBlogs(blogs.data).subscribe()
        }.subscribe()
    }

}