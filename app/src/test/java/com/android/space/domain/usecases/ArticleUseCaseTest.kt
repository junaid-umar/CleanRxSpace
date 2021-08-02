package com.android.space.domain.usecases

import com.android.space.domain.repository.ArticleRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ArticleUseCaseTest {

    // System under test
    private lateinit var articleUseCase: ArticleUseCase
    private val mockArticleRepository = Mockito.mock(ArticleRepository::class.java)
    private val limit = 1
    private val page = 1
    private val query = "search article"

    @BeforeEach
    fun setUp() {
        articleUseCase = ArticleUseCase(mockArticleRepository)
    }

    @Test
    fun `search articles`() {

        articleUseCase.searchArticles(limit, page, query)

        Mockito.verify(mockArticleRepository).searchArticles(limit, page, query)
    }
}