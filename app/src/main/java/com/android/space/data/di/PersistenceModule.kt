package com.android.space.data.di

import androidx.room.Room
import com.android.space.data.persistence.dao.BlogDao
import com.android.space.data.persistence.database.AppDatabase
import com.android.space.data.persistence.datasource.BlogPersistenceDataSourceImpl
import com.android.space.data.persistence.entity.BlogEntityMapper
import com.android.space.data.persistence.datasource.BlogPersistenceDataSource
import com.android.space.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Singleton
    @Provides
    fun provideDb(app: BaseApplication): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideBlogDao(db: AppDatabase): BlogDao {
        return db.blogDao()
    }

    @Singleton
    @Provides
    fun provideBlogEntityMapper(): BlogEntityMapper {
        return BlogEntityMapper()
    }


    @Singleton
    @Provides
    fun provideBlogPersistenceDataSource(
        blogDao: BlogDao,
        blogEntityMapper: BlogEntityMapper
    ): BlogPersistenceDataSource {
        return BlogPersistenceDataSourceImpl(blogDao, blogEntityMapper)
    }


}