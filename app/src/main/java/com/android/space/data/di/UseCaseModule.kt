package com.android.space.data.di

import com.android.space.domain.repository.BlogRepository
import com.android.space.domain.usecases.SearchBlogUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideSearchBlogUseCase(blogRepository: BlogRepository): SearchBlogUseCase {
        return SearchBlogUseCase(
            blogRepository
        )
    }

}