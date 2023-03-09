package com.dart69.data

import com.dart69.data.models.CityLocationResponse

interface LocationDataSource {
    suspend fun findLocations(location: String): List<CityLocationResponse>
}

interface LocationRemoteDataSource : LocationDataSource {

    class Default(
        private val locationApi: LocationApi,
        private val apiCallWrapper: ApiCallWrapper,
        private val apiKey: String,
    ) : LocationRemoteDataSource {
        override suspend fun findLocations(location: String): List<CityLocationResponse> =
            apiCallWrapper.wrapExceptions {
                locationApi.findLocations(location, LIMIT, apiKey)
            }

        private companion object {
            const val LIMIT = 5
        }
    }
}