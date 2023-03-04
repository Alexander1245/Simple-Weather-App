package com.dart69.data

import com.dart69.data.models.CityLocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {

    @GET(PATH)
    suspend fun findLocations(
        @Query(LOCATION_QUERY) location: String,
        @Query(LIMIT) limit: Int,
        @Query(APP_ID_QUERY) apiKey: String,
    ): Response<List<CityLocationResponse>>

    private companion object {
        const val PATH = "geo/1.0/direct"
        const val LOCATION_QUERY = "q"
        const val LIMIT = "limit"
        const val APP_ID_QUERY = "appid"
    }
}