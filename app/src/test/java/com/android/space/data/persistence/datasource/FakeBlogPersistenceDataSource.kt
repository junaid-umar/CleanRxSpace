package com.android.space.data.persistence.datasource

import com.android.space.data.persistence.entity.BlogEntity
import com.android.space.data.persistence.entity.BlogEntityMapper
import com.android.space.data.persistence.entity.mapFromDomainList
import com.android.space.domain.model.Blog
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class FakeBlogPersistenceDataSource : BlogPersistenceDataSource {
    private val blogsDb = mutableListOf<BlogEntity>()
    private val blogEntityMapper = BlogEntityMapper()


    override fun insertBlog(blog: Blog): Completable {
        blogsDb.removeIf {
            it.id == blog.id
        }
        blogsDb.add(blogEntityMapper.mapFromDomainModel(blog))
        return Completable.complete()
    }

    override fun insertBlogs(blogs: List<Blog>): Completable {
        blogsDb.removeIf { old ->
            blogs.any { new ->
                old.id == new.id
            }
        }
        blogsDb.addAll(blogs.mapFromDomainList())
        return Completable.complete()
    }

    override fun searchBlogs(pageSize: Int, page: Int, query: String): Observable<List<Blog>> {
        return Observable.fromCallable {
            blogsDb.filter {
                it.title.contains(query)
            }.map {
                blogEntityMapper.mapToDomainModel(it)
            }
        }
    }
}