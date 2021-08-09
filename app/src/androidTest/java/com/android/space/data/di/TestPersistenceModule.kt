package com.android.space.data.di

import android.content.Context
import androidx.room.Room
import com.android.space.data.persistence.dao.BlogDao
import com.android.space.data.persistence.database.AppDatabase
import com.android.space.data.persistence.datasource.BlogPersistenceDataSource
import com.android.space.data.persistence.datasource.BlogPersistenceDataSourceImpl
import com.android.space.data.persistence.entity.BlogEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Named

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [PersistenceModule::class]
)
object TestPersistenceModule {

    @Provides
    fun provideInMemoryDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()


    @Provides
    fun provideBlogDao(db: AppDatabase): BlogDao {
        return db.blogDao()
    }

    @Provides
    fun provideBlogEntityMapper(): BlogEntityMapper {
        return BlogEntityMapper()
    }


    @Provides
    fun provideBlogPersistenceDataSource(
        blogDao: BlogDao,
        blogEntityMapper: BlogEntityMapper
    ): BlogPersistenceDataSource {
        return BlogPersistenceDataSourceImpl(blogDao, blogEntityMapper)
    }

}