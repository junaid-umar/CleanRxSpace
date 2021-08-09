package com.android.space.data.persistence.dao

import androidx.annotation.VisibleForTesting
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.space.data.persistence.entity.BlogEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBlog(blog: BlogEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBlogs(blogs: List<BlogEntity>) : Completable

    @Query("SELECT * FROM blog WHERE title LIKE '%' || :query || '%' LIMIT :pageSize OFFSET (:page-1) * :pageSize")
    fun searchBlogs(pageSize: Int, page: Int, query: String): Single<List<BlogEntity>>

    @VisibleForTesting
    @Query("SELECT * FROM blog WHERE id = :id")
    fun searchBlog(id: Int): Single<BlogEntity>
}