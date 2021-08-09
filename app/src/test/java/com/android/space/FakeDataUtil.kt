package com.android.space

import com.android.space.data.network.dto.BlogDto
import com.android.space.data.persistence.entity.BlogEntity
import com.android.space.domain.model.Blog
import java.text.SimpleDateFormat
import java.util.*

object FakeDataUtil {
    object BlogEntity {
        val blog1 = BlogEntity(
            1, "title1", "img ulr 1", "summary1", Date.longDate
        )

        val blog2 = BlogEntity(
            2, "title2", "img ulr 2", "summary2",  Date.longDate
        )

        fun getFakeBlogEntities() = listOf(blog1, blog2)
    }

    object Domain {
        val blog1 = Blog(
            1, "title1", "img ulr 1", "summary1", Date.date
        )
        val blog2 = Blog(
            2, "title2", "img ulr 2", "summary2", Date.date
        )

        fun getFakeBlogList() = listOf(blog1, blog2)
    }
    object Dto {
        val blog1 = BlogDto(
            1, "title1", "img ulr 1", "summary1", Date.stringDate
        )
        val blog2 = BlogDto(
            2, "title2", "img ulr 2", "summary2", Date.stringDate
        )

        fun getFakeBlogList() = listOf(blog1, blog2)
    }


    object Common {
        val pageSize = 10;
        val page = 1
        val querySearch = "title"

    }
    object Date{
         val stringDate = "2021-07-31T21:50:11.265Z"
         val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
         val date = format.parse(stringDate)
         val longDate = date!!.time
    }
}