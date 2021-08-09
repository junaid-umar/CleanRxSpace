package com.android.space.data.persistence.datasource

import com.android.space.data.persistence.dao.BlogDao
import com.android.space.data.persistence.entity.BlogEntityMapper
import com.android.space.data.persistence.entity.mapFromDomainList
import com.android.space.data.persistence.entity.mapToDomainList
import com.android.space.domain.model.Blog
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class BlogPersistenceDataSourceImpl
@Inject
constructor(
    private val blogDao: BlogDao,
    private val blogEntityMapper: BlogEntityMapper
) : BlogPersistenceDataSource {
    override fun insertBlog(blog: Blog): Completable {
        return blogDao.insertBlog(blogEntityMapper.mapFromDomainModel(blog))

    }

    override fun insertBlogs(blogs: List<Blog>): Completable {
        return blogDao.insertBlogs(blogs.mapFromDomainList())

    }

    override fun searchBlogs(
        pageSize: Int,
        page: Int,
        query: String
    ): Observable<List<Blog>> {
        return blogDao.searchBlogs(pageSize, page, query).map {
            it.mapToDomainList()
        }.toObservable()
    }
}


