package com.android.space.domain.usecases

import com.android.space.domain.repository.BlogRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class BlogUseCaseTest {

    // System under test
    private lateinit var blogUseCase: BlogUseCase
    private val mockBlogRepository = Mockito.mock(BlogRepository::class.java)
    private val limit =1
    private val page =1
    private val query ="search blogs"

    @BeforeEach
    internal fun setUp() {
        blogUseCase = BlogUseCase(mockBlogRepository)
    }

    @Test
    internal fun `search blogs`() {

        blogUseCase.searchBlogs(limit,page, query)

        Mockito.verify(mockBlogRepository).searchBlogs(limit, page, query)
    }
}