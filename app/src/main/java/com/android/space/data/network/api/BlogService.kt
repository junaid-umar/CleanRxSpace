package com.android.space.data.network.api

import com.android.space.data.network.dto.BlogDto
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BlogService {
    @GET("blogs")
    fun searchBlog(
        @Query("_limit") pageSize: Int,
        @Query("_start") page: Int,
        @Query("title_contains") query: String
    ): Single<Response<List<BlogDto>>>
}