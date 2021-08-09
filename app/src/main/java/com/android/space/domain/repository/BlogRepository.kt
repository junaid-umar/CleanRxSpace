package com.android.space.domain.repository

import com.android.space.domain.model.Blog
import com.android.space.domain.model.Resource
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface BlogRepository {
    fun searchBlogs(pageSize: Int, page: Int, query: String): Observable<Resource<List<Blog>>>
}