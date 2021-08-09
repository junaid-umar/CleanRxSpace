package com.android.space.data.persistence.datasource

import com.android.space.domain.model.Blog
import com.android.space.domain.model.Resource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface BlogPersistenceDataSource {
    fun insertBlog(blog: Blog) : Completable
    fun insertBlogs(blogs: List<Blog>) : Completable
    fun searchBlogs(pageSize: Int, page: Int, query: String): Observable<List<Blog>>
}