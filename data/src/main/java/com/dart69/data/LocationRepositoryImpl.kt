package com.dart69.data

import com.dart69.coroutines.context.AvailableDispatchers
import com.dart69.coroutines.flows.MutableDebounceFlow
import com.dart69.coroutines.flows.ResultsFlow
import com.dart69.coroutines.flows.resultsFlowOf
import com.dart69.data.models.CityLocationResponse
import com.dart69.domain.LocationRepository
import com.dart69.domain.models.CityLocation
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn

@OptIn(FlowPreview::class)
class LocationRepositoryImpl(
    private val dispatchers: AvailableDispatchers,
    private val remoteDataSource: LocationRemoteDataSource,
    private val modelMappers: Mapper<CityLocationResponse, CityLocation>,
    private val searchQuery: MutableDebounceFlow<String>,
) : LocationRepository {
    private val locations = searchQuery
        .flatMapConcat {
            resultsFlowOf {
                remoteDataSource.findLocations(it).map(modelMappers::map)
            }.flowOn(dispatchers.io)
        }

    override fun observeLocations(): ResultsFlow<List<CityLocation>> = locations

    override fun searchLocations(query: String) = searchQuery.emit(query)
}