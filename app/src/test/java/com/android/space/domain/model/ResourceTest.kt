package com.android.space.domain.model

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class ResourceTest {

    private val data = "data"
    private val message = "message"


    @Test
     fun `transformed data returns resource success`() {
        val successResource = Resource.Success(data)

        val transformedResource = successResource.transform { it.plus(it) }

        assertThat((transformedResource as Resource.Success).data).isEqualTo(data.plus(data))
    }

    @Test
     fun `transformed data returns resource error`() {
        val errorResource = Resource.Error(message, data)

        val transformedResource = errorResource.transform { data.plus(data) }

        assertThat((transformedResource as Resource.Error).data).isEqualTo(data.plus(data))
        assertThat(transformedResource.message).isEqualTo(message)
    }

    @Test
     fun `transformed data returns resource loading`() {
        val loadingResource = Resource.Loading(data)

        val transformedResource = loadingResource.transform { data.plus(data) }

        assertThat((transformedResource as Resource.Loading).data).isEqualTo(data.plus(data))

    }
}