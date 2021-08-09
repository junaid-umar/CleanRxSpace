package com.android.space.data.network.dto

import com.android.space.domain.model.Blog
import com.android.space.domain.util.DateUtil
import com.android.space.domain.util.DomainMapper

class BlogDtoMapper : DomainMapper<BlogDto, Blog> {
    override fun mapToDomainModel(model: BlogDto): Blog {
        return Blog(
            id = model.id,
            title = model.title,
            imgUrl = model.imgUrl,
            summary = model.summary,
            publishedAt = DateUtil.stringToDate(model.publishedAt)
        )
    }

    override fun mapFromDomainModel(domain: Blog): BlogDto {
        return BlogDto(
            id = domain.id,
            title = domain.title,
            imgUrl = domain.imgUrl,
            summary = domain.summary,
            publishedAt = DateUtil.dateToString(domain.publishedAt),
        )
    }
}
fun List<BlogDto>.mapToDomainList() = this.map {
    BlogDtoMapper().mapToDomainModel(it)
}
