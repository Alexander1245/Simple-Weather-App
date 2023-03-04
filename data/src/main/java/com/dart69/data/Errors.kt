package com.dart69.data

import retrofit2.Response

abstract class ApiError : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
}

class BlankLocationError : ApiError()

class ApiKeyError : ApiError()

class WrongLocationError : ApiError()

class TooManyCallsPerMinuteError : ApiError()

class InternalServerError : ApiError()

interface ApiErrorFactory {
    fun create(errorCode: Int): ApiError

    class Default : ApiErrorFactory {
        override fun create(errorCode: Int): ApiError =
            when (errorCode) {
                400 -> BlankLocationError()
                401 -> ApiKeyError()
                404 -> WrongLocationError()
                429 -> TooManyCallsPerMinuteError()
                500, in 502..504 -> InternalServerError()
                else -> throw IllegalArgumentException("Unknown error code $errorCode.")
            }
    }
}

interface ApiCallWrapper {
    suspend fun <T> wrapExceptions(block: suspend () -> Response<T>): T

    class Default(
        private val errorFactory: ApiErrorFactory
    ) : ApiCallWrapper {
        override suspend fun <T> wrapExceptions(block: suspend () -> Response<T>): T {
            val response = block()
            return if (response.isSuccessful) {
                response.body()!!
            } else {
                throw errorFactory.create(response.code())
            }
        }
    }
}