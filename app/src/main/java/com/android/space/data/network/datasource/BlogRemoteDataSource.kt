package com.android.space.data.network.datasource

import com.android.space.domain.model.Blog
import com.android.space.domain.model.Resource
import io.reactivex.rxjava3.core.Single

interface BlogRemoteDataSource {
    fun searchBlogs(pageSize: Int, page: Int, query: String): Single<Resource<List<Blog>>>
}