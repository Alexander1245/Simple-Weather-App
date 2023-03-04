package com.dart69.data

import com.dart69.data.models.CityLocationResponse
import com.dart69.domain.models.CityLocation
import com.dart69.domain.models.Coordinates

interface Mapper<I, O> {
    fun map(from: I): O
}

class LocationModelMapper : Mapper<CityLocationResponse, CityLocation> {
    override fun map(from: CityLocationResponse): CityLocation =
        CityLocation(
            name = from.name,
            country = from.country,
            coordinates = Coordinates(
                longitude = from.lon,
                latitude = from.lat,
            ),
        )
}