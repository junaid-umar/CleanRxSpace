package com.android.space.data.persistence.entity

import com.android.space.domain.model.Blog
import com.android.space.domain.util.DateUtil
import com.android.space.domain.util.DomainMapper

class BlogEntityMapper : DomainMapper<BlogEntity, Blog> {
    override fun mapToDomainModel(model: BlogEntity): Blog {
        return Blog(
            id = model.id,
            title = model.title,
            imgUrl = model.imgUrl,
            summary = model.summary,
            publishedAt = DateUtil.longToDate(model.publishedAt)
        )
    }

    override fun mapFromDomainModel(domain: Blog): BlogEntity {
        return BlogEntity(
            id = domain.id,
            title = domain.title,
            imgUrl = domain.imgUrl,
            summary = domain.summary,
            publishedAt = DateUtil.dateToLong(domain.publishedAt)
        )
    }


}

fun List<BlogEntity>.mapToDomainList() = this.map {
    BlogEntityMapper().mapToDomainModel(it)
}

fun List<Blog>.mapFromDomainList() = this.map {
    BlogEntityMapper().mapFromDomainModel(it)
}