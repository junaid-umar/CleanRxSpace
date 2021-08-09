package com.android.space.data.persistence

import com.android.space.data.persistence.entity.BlogEntity
import com.android.space.domain.model.Blog
import java.util.*

object FakeDataUtil {
    object BlogEntity {
        val blog1 = BlogEntity(
            1, "title1", "img ulr 1", "summary1", 1
        )

        val blog2 = BlogEntity(
            2, "title2", "img ulr 2", "summary2", 2
        )

        fun getFakeBlogEntities() = listOf(blog1, blog2)
    }

    object Domain {
        val blog1 = Blog(
            1, "title1", "img ulr 1", "summary1", Date()
        )
        val blog2 = Blog(
            2, "title2", "img ulr 2", "summary2", Date()
        )

        fun getFakeBlogList() = listOf(blog1, blog2)
    }


    object Common {
        val pageSize = 10
        val page = 1
        val querySearch = "title"

    }
}