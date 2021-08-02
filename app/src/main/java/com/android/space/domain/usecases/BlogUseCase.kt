package com.android.space.domain.usecases

import com.android.space.domain.repository.BlogRepository
import javax.inject.Inject

class BlogUseCase
@Inject constructor(private val blogRepository: BlogRepository) {

    fun searchBlogs(limit: Int, page: Int, query: String) =
        blogRepository.searchBlogs(limit, page, query)
}