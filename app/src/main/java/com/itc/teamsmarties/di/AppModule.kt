package com.itc.teamsmarties.di

import com.itc.teamsmarties.data.remote.ApiDetails
import com.itc.teamsmarties.data.remote.RedditService
import com.itc.teamsmarties.data.repository.RedditRepository
import com.itc.teamsmarties.data.repository.RedditRepositoryImpl
import com.itc.teamsmarties.util.dataMapper.PostDataMapper
import com.itc.teamsmarties.util.dataMapper.PostDataMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideRedditService(): RedditService {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RedditService::class.java)
    }

    //Merging the repository class together
    @Provides
    fun provideRepository(
        redditService: RedditService,
        postDataMapper: PostDataMapper
    ): RedditRepository {
        return RedditRepositoryImpl(
            redditService,
            postDataMapper
        )
    }

    @Provides
    fun provideDataMapper(): PostDataMapper {
        return PostDataMapperImpl()
    }
}