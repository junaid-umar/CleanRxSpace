package com.android.space.domain.usecases

import com.android.space.domain.repository.BlogRepository
import javax.inject.Inject

class SearchBlogUseCase
@Inject constructor(private val blogRepository: BlogRepository) {

    fun searchBlogs(pageSize: Int, page: Int, query: String) =
        blogRepository.searchBlogs(pageSize, page, query)
}