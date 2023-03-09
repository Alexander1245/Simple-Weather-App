package com.dart69.data.models

data class CityLocationResponse(
    val country: String,
    val lat: Double,
    val local_names: LocalNames?,
    val lon: Double,
    val name: String,
    val state: String?
)