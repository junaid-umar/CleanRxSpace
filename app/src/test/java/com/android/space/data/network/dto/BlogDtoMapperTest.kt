package com.android.space.data.network.dto



import com.android.space.FakeDataUtil
import com.android.space.domain.model.Blog
import com.android.space.domain.util.DateUtil
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlogEntityMapperTest {

    // System under Test
    private lateinit var blogDtoMapper: BlogDtoMapper

    @BeforeEach
    fun setUp() {
        blogDtoMapper= BlogDtoMapper()
    }

    @Test
    fun `blog dto returns blog`() {
        val input = FakeDataUtil.Dto.blog1

        val output = blogDtoMapper.mapToDomainModel(input)

        input.compareTo(output)
    }

    @Test
    fun `blog dtos returns blogs`() {
        val input = FakeDataUtil.Dto.getFakeBlogList()
        val output = input.mapToDomainList()

        assertThat(output).hasSize(input.size)

        val dto = input[0]
        val model = output[0]
        dto.compareTo(model)
    }

    @Test
    fun `blogs returns blog dtos`() {
        val input = FakeDataUtil.Domain.blog1

        val output = blogDtoMapper.mapFromDomainModel(input)

        output.compareTo(input)
    }





    private fun BlogDto.compareTo(model: Blog) {
        assertThat(model).isNotNull()

        assertThat(id).isEqualTo(model.id)
        assertThat(title).isEqualTo(model.title)
        assertThat(summary).isEqualTo(model.summary)
        assertThat(imgUrl).isEqualTo(model.imgUrl)
        assertThat(publishedAt).isEqualTo(DateUtil.dateToString(model.publishedAt))
    }
}