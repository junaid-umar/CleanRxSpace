package com.android.space.data.network.datasource

import com.android.space.data.network.api.BlogService
import com.android.space.data.network.dto.mapToDomainList
import com.android.space.data.util.NetworkUtil
import com.android.space.domain.model.Blog
import com.android.space.domain.model.Resource
import com.android.space.domain.model.transform
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class BlogRemoteDataSourceImpl
@Inject
constructor(
    private val blogService: BlogService
) : BlogRemoteDataSource {
    override fun searchBlogs(
        pageSize: Int,
        page: Int,
        query: String
    ): Single<Resource<List<Blog>>> {
        return blogService.searchBlog(
            pageSize, page, query
        ).map {
            if (it.isSuccessful) Resource.Success(it.body()!!)
            else Resource.Error(NetworkUtil.getErrorMessage(it.code()))
        }.map {
            it.transform {
                it.mapToDomainList()
            }
        }.onErrorReturn {
            Resource.Error(NetworkUtil.getNetworkErrorMessage(it))
        }.subscribeOn(Schedulers.io())
    }
}