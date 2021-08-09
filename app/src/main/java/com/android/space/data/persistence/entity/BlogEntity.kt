package com.android.space.data.persistence.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = BlogEntity.BLOG_TABLE)
class BlogEntity(

    @PrimaryKey
    @ColumnInfo(name = BLOG_ID)
    val id: Int,

    @ColumnInfo(name = BLOG_TITLE)
    val title: String,

    @ColumnInfo(name = BLOG_IMG_URL)
    val imgUrl: String,

    @ColumnInfo(name = BLOG_SUMMARY)
    val summary: String,

    @ColumnInfo(name = BLOG_PUBLISHED_AT)
    val publishedAt: Long,

    ) {
    companion object {
        const val BLOG_TABLE = "blog"

        const val BLOG_ID = "id"
        const val BLOG_TITLE = "title"
        const val BLOG_IMG_URL = "img_url"
        const val BLOG_SUMMARY = "summary"
        const val BLOG_PUBLISHED_AT = "published_at"


    }

}