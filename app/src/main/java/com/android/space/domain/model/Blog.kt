package com.android.space.domain.model

import java.util.*

data class Blog(
    val id: Int,
    val title: String,
    val imgUrl: String,
    val summary: String,
    val publishedAt: Date,
)
