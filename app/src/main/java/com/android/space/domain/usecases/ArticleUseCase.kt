package com.android.space.domain.usecases

import com.android.space.domain.repository.ArticleRepository
import javax.inject.Inject

class ArticleUseCase
@Inject
constructor(
    private val articleRepository: ArticleRepository
) {
    fun searchArticles(limit: Int, page: Int, query: String) =
        articleRepository.searchArticles(limit, page, query)
}