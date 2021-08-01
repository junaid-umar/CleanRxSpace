package com.android.space.domain.model

import java.util.*

data class Report(
    val id: Int,
    val title: String,
    val refUrl: String,
    val imgUrl: String,
    val summary: String,
    val publishedAt: Date,
    val updatedAt: Date
)
