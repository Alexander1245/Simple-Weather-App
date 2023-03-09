package com.dart69.simpleweatherapp.core.di

import com.dart69.coroutines.context.AvailableDispatchers
import com.dart69.data.ApiCallWrapper
import com.dart69.data.ApiErrorFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiKey

@Module
@InstallIn(SingletonComponent::class)
object CoreProviders {
    private const val API_KEY = "77769532b98356ae8c6f12dc4d511117"
    private const val BASE_URL = "http://api.openweathermap.org/"

    @Provides
    @ApiKey
    fun provideApiKey(): String = API_KEY

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideDispatchers(): AvailableDispatchers =
        AvailableDispatchers.AppDispatchers()

    @Provides
    fun provideErrorFactory(): ApiErrorFactory = ApiErrorFactory.Default()

    @Provides
    fun provideApiCallWrapper(
        errorFactory: ApiErrorFactory
    ): ApiCallWrapper = ApiCallWrapper.Default(errorFactory)
}