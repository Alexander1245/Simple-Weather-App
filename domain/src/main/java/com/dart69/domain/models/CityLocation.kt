package com.dart69.domain.models

import java.io.Serializable

data class CityLocation(
    val name: String,
    val country: String,
    val state: String,
    val coordinates: Coordinates,
): Serializable
