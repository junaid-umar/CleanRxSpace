package com.android.space.domain.util


import com.android.space.FakeDataUtil
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test


class DateUtilTest {


    @Test
    fun `correct string returns date`() {
        val input = FakeDataUtil.Date.stringDate
        val output = FakeDataUtil.Date.date
        val result = DateUtil.stringToDate(
            input
        )
        assertThat(result).isEqualTo(output)

    }


    @Test
    fun `date returns long`() {
        val input = FakeDataUtil.Date.date
        val output = FakeDataUtil.Date.longDate
        val result = DateUtil.dateToLong(input)

        assertThat(result).isEqualTo(output)
    }


    @Test
    fun `date returns string`() {
        val input = FakeDataUtil.Date.date
        val output = FakeDataUtil.Date.stringDate
        val result = DateUtil.dateToString(input)

        assertThat(result).isEqualTo(output)
    }

    @Test
    fun `long returns date`() {
        val input = FakeDataUtil.Date.longDate
        val output = FakeDataUtil.Date.date
        val result = DateUtil.longToDate(input)

        assertThat(result).isEqualTo(output)
    }
}