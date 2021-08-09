package com.android.space.data.network.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BlogDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("imageUrl")
    val imgUrl: String,

    @SerializedName("summary")
    val summary: String,

    @SerializedName("publishedAt")
    val publishedAt: String,

    )