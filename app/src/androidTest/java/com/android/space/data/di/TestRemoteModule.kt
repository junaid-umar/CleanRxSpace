package com.android.space.data.di

import com.android.space.BuildConfig
import com.android.space.data.network.api.BlogService
import com.android.space.data.network.datasource.BlogRemoteDataSource
import com.android.space.data.network.datasource.BlogRemoteDataSourceImpl
import com.android.space.data.network.util.CustomDispatcher
import com.android.space.data.network.util.MockNetworkConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RemoteModule::class]
)
class TestRemoteModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = provideHttpInterceptor()

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

    }

    @Singleton
    @Provides
    fun provideHttpInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        httpLoggingInterceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideMockServer(): MockWebServer {
        return MockWebServer().apply {
            dispatcher = CustomDispatcher()
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, mockWebServer: MockWebServer): Retrofit {
        return Retrofit.Builder().baseUrl(mockWebServer.url(MockNetworkConfig.randomBaseUrl))
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit): BlogService {
        return retrofit.create(BlogService::class.java)
    }


    @Singleton
    @Provides
    fun provideBlogRemoteDatasource(blogService: BlogService): BlogRemoteDataSource {
        return BlogRemoteDataSourceImpl(
            blogService
        )
    }

}