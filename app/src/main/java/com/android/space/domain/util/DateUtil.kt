package com.android.space.domain.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


object DateUtil {

    // format 2021-07-31T21:50:11.265Z
    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    fun stringToDate(stringDate: String): Date{
        return sdf.parse(stringDate) ?: throw NullPointerException("Date cannot be converted")
    }

    fun dateToString(date: Date): String {
        return sdf.format(date)
    }

    fun dateToLong(date: Date): Long {
        return date.time
    }

    fun longToDate(long: Long): Date {
        return Date(long)
    }

}
