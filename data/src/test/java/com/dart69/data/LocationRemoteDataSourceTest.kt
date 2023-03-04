package com.dart69.data

import com.dart69.data.models.CityLocationResponse
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

internal class FakeLocationApi : LocationApi {
    var errorCode = 401

    override suspend fun findLocations(
        location: String,
        limit: Int,
        apiKey: String
    ): Response<List<CityLocationResponse>> =
        Response.error(errorCode, ResponseBody.create(null, ""))
}

internal class LocationRemoteDataSourceTest {
    private lateinit var dataSource: LocationRemoteDataSource.Default
    private lateinit var api: FakeLocationApi

    @Before
    fun beforeEach() {
        api = FakeLocationApi()
        dataSource = LocationRemoteDataSource.Default(
            locationApi = api,
            apiCallWrapper = ApiCallWrapper.Default(ApiErrorFactory.Default()),
            apiKey = "",
        )
    }

    @Test
    fun `a dataSource wraps exceptions to the ApiError`() = runBlocking {
        val errorCodes = listOf(401, 404, 429,  500, 502, 503, 504)
        errorCodes.forEach {
            try {
                api.errorCode = it
                dataSource.findLocations("")
            } catch (throwable: Throwable) {
                assertTrue(throwable is ApiError)
            }
        }
    }
}