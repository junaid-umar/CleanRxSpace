package com.android.space.domain.repository

import com.android.space.domain.model.Article
import com.android.space.domain.model.Resource
import io.reactivex.rxjava3.core.Single

interface ArticleRepository {
    fun searchArticles(limit: Int, page: Int, query: String): Single<Resource<List<Article>>>
}