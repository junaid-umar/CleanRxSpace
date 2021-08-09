package com.android.space.data.di

import com.android.space.data.repository.BlogRepositoryImpl
import com.android.space.data.network.datasource.BlogRemoteDataSource
import com.android.space.data.persistence.datasource.BlogPersistenceDataSource
import com.android.space.domain.repository.BlogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {


    @ViewModelScoped
    @Provides
    fun provideBlogRepository(
        blogRemoteDataSource: BlogRemoteDataSource,
        blogPersistenceDataSource: BlogPersistenceDataSource
    ): BlogRepository {
        return BlogRepositoryImpl(
            blogPersistenceDataSource,
            blogRemoteDataSource
        )
    }
}