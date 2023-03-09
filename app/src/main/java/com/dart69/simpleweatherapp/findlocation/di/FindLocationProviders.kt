package com.dart69.simpleweatherapp.findlocation.di

import com.dart69.coroutines.context.AvailableDispatchers
import com.dart69.coroutines.flows.MutableDebounceFlow
import com.dart69.data.*
import com.dart69.data.models.CityLocationResponse
import com.dart69.domain.LocationRepository
import com.dart69.domain.models.CityLocation
import com.dart69.simpleweatherapp.core.di.ApiKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FindLocationProviders {
    private const val SEARCH_TIMEOUT = 500L

    @Provides
    @Singleton
    fun provideLocationApi(
        retrofit: Retrofit
    ): LocationApi = retrofit.create()

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        api: LocationApi,
        apiCallWrapper: ApiCallWrapper,
        @ApiKey apiKey: String,
    ): LocationRemoteDataSource =
        LocationRemoteDataSource.Default(
            api,
            apiCallWrapper,
            apiKey
        )

    @Provides
    fun provideModelMapper(): Mapper<CityLocationResponse, CityLocation> = LocationModelMapper()

    @Provides
    @Singleton
    fun provideLocationRepository(
        dispatchers: AvailableDispatchers,
        remoteDataSource: LocationRemoteDataSource,
        modelMapper: Mapper<CityLocationResponse, CityLocation>,
    ): LocationRepository =
        LocationRepositoryImpl(
            dispatchers,
            remoteDataSource,
            modelMapper,
            MutableDebounceFlow(SEARCH_TIMEOUT),
        )
}