package com.android.space.domain.repository

import com.android.space.domain.model.Blog
import com.android.space.domain.model.Resource
import io.reactivex.rxjava3.core.Single

interface BlogRepository {
    fun searchBlogs(limit: Int, page: Int, query: String): Single<Resource<List<Blog>>>
}