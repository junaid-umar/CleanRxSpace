package com.android.space.data.persistence.entity

import com.android.space.FakeDataUtil
import com.android.space.domain.model.Blog
import com.android.space.domain.util.DateUtil
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlogEntityMapperTest {

    // System under Test
    private lateinit var blogEntityMapper: BlogEntityMapper

    @BeforeEach
    internal fun setUp() {
        blogEntityMapper = BlogEntityMapper()
    }

    @Test
    fun `blog entity returns blog`() {
        val input = FakeDataUtil.BlogEntity.blog1

        val output = blogEntityMapper.mapToDomainModel(input)

        input.compareTo(output)
    }

    @Test
    fun `blog entities returns blogs`() {
        val input = FakeDataUtil.BlogEntity.getFakeBlogEntities()
        val output = input.mapToDomainList()

        assertThat(output).hasSize(input.size)

        val entity = input[0]
        val model = output[0]
        entity.compareTo(model)
    }

    @Test
    fun `blogs returns blog entity`() {
        val input = FakeDataUtil.Domain.blog1

        val output = blogEntityMapper.mapFromDomainModel(input)

        output.compareTo(input)
    }


    @Test
    fun `blogs returns blogs entities`() {
        val input = FakeDataUtil.Domain.getFakeBlogList()
        val output = input.mapFromDomainList()

        assertThat(output).hasSize(input.size)

        val entity = input[0]
        val model = output[0]
        model.compareTo(entity)
    }



    private fun BlogEntity.compareTo(model: Blog) {
        assertThat(model).isNotNull()

        assertThat(id).isEqualTo(model.id)
        assertThat(title).isEqualTo(model.title)
        assertThat(summary).isEqualTo(model.summary)
        assertThat(imgUrl).isEqualTo(model.imgUrl)
        assertThat(publishedAt).isEqualTo(DateUtil.dateToLong(model.publishedAt))
    }
}