package com.android.space.domain.usecases

import com.android.space.FakeDataUtil
import com.android.space.domain.repository.BlogRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class SearchBlogUseCaseTest {

    private lateinit var searchBlogUseCase: SearchBlogUseCase

    private lateinit var mockBlogRepository: BlogRepository
    private val pageSize = FakeDataUtil.Common.pageSize
    private val page = FakeDataUtil.Common.page
    private val query = FakeDataUtil.Common.querySearch

    @BeforeEach
    fun setUp() {
        mockBlogRepository = Mockito.mock(BlogRepository::class.java)
        searchBlogUseCase = SearchBlogUseCase(mockBlogRepository)

    }

    @Test
    fun `search blogs`() {

        searchBlogUseCase.searchBlogs(pageSize, page, query)

        Mockito.verify(mockBlogRepository).searchBlogs(pageSize, page, query)
        Mockito.verifyNoMoreInteractions(mockBlogRepository)

    }
}