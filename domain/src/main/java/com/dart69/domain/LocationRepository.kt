package com.dart69.domain

import com.dart69.coroutines.flows.ResultsFlow
import com.dart69.domain.models.CityLocation

interface LocationRepository {
    fun observeLocations(): ResultsFlow<List<CityLocation>>

    fun searchLocations(query: String)
}