package com.android.space.domain.util


import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat


class DateUtilTest {

    private val stringDate = "2021-07-31T21:50:11.265Z"
    private val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private val date = format.parse(stringDate)
    private val longDate = date!!.time


    /*
    * converts string to date
    * */

    @Test
    internal fun `string returns date`() {
        val result = DateUtil.stringToDate(
            stringDate
        )
        assertThat(result).isEqualTo(date)

    }

    /*
    * converts date to long
    * */
    @Test
    internal fun `date returns long`() {
        val result = DateUtil.dateToLong(date)

        assertThat(result).isEqualTo(longDate)
    }


    /*
    * converts date to string
    * */
    @Test
    internal fun `date returns string`() {
        val result = DateUtil.dateToString(date)

        assertThat(result).isEqualTo(stringDate)
    }

    /*
    * converts long to date
    * */
    @Test
    internal fun `long returns date`() {
        val result = DateUtil.longToDate(longDate)

        assertThat(result).isEqualTo(date)
    }
}