package com.dart69.data

import com.dart69.coroutines.context.AvailableDispatchers
import com.dart69.coroutines.flows.MutableDebounceFlow
import com.dart69.coroutines.results.Results
import com.dart69.coroutines.results.takeCompleted
import com.dart69.data.models.CityLocationResponse
import com.dart69.domain.models.CityLocation
import com.dart69.domain.models.Coordinates
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class TestDispatchers(
    dispatcher: TestDispatcher = StandardTestDispatcher()
) : AvailableDispatchers {
    override val default: CoroutineDispatcher = dispatcher
    override val io: CoroutineDispatcher = dispatcher
    override val main: CoroutineDispatcher = dispatcher
    override val unconfined: CoroutineDispatcher = dispatcher
}

internal class FakeRemoteDataSource : LocationRemoteDataSource {
    override suspend fun findLocations(location: String): List<CityLocationResponse> {
        return listOfNotNull(
            when (location) {
                "Moscow", "Saratov", "Piter" -> RUSSIA.copy(name = location)
                else -> null
            }
        )
    }

    companion object {
        val RUSSIA = CityLocationResponse("Russia", 1.0, null, 1.0, "", "")
        val SARATOV = RUSSIA.copy(name = "Saratov")
        val MOSCOW = RUSSIA.copy(name = "Moscow")
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
internal class LocationRepositoryImplTest {
    private lateinit var repository: LocationRepositoryImpl
    private val russia = LocationModelMapper().map(FakeRemoteDataSource.RUSSIA)

    private fun flatMapResults(input: List<List<CityLocation>>): List<String> =
        input.flatMap { list -> list.map { it.name } }
    @Before
    fun beforeEach() {
        repository = LocationRepositoryImpl(
            dispatchers = TestDispatchers(UnconfinedTestDispatcher()),
            remoteDataSource = FakeRemoteDataSource(),
            modelMappers = LocationModelMapper(),
            searchQuery = MutableDebounceFlow(50L),
        )
    }

    @Test
    fun `searchLocation is debounced`() = runBlocking {
        val locations = listOf("Mos", "Mosco", "Moscow")
        val expected = listOf(russia.copy(name = locations.last()))
        var collectorCalls = 0
        val actual = async {
            repository.observeLocations()
                .filter { it is Results.Completed }
                .map { it.takeCompleted()!! }
                .onEach { ++collectorCalls }
                .first()
        }
        locations.forEach(repository::searchLocations)
        assertEquals(expected.first(), actual.await().first())
        assertTrue(collectorCalls == 1)
    }

    @Test
    fun `searchLocation is distinct until changed`() = runBlocking {
        val default = CityLocation("", "","", Coordinates(0.0, 0.0))
        val locations = listOf(
            List(3) { "Moscow" }.map { default.copy(name = it) },
            listOf(default.copy(name = "Saratov")),
        )
        val expected = listOf(
            locations.component1().distinct(),
            locations.component2(),
        )
        val actual = async {
            repository.observeLocations()
                .filter { it is Results.Completed }
                .map { it.takeCompleted()!! }
                .take(expected.size)
                .toList()
        }
        locations.flatten().map { it.name }.forEach {
            delay(100L)
            repository.searchLocations(it)
        }
        assertEquals(expected.size, actual.await().size)
        assertEquals(flatMapResults(expected), flatMapResults(actual.await()))
    }

    @Test
    fun `test debounce`() = runBlocking {
        val mapper = LocationModelMapper()
        val firstLocations = listOf("Mos", "Mosc", "Moscow")
        val secondLocations = listOf("Sara", "Saratov")
        val expected = listOf(
            Results.Loading(),
            Results.Completed(listOf( mapper.map(FakeRemoteDataSource.MOSCOW))),
            Results.Loading(),
            Results.Completed(listOf(mapper.map(FakeRemoteDataSource.SARATOV)))
        )
        val actual = async {
            repository.observeLocations().take(expected.size).toList()
        }
        firstLocations.forEach(repository::searchLocations)
        delay(150L)
        secondLocations.forEach(repository::searchLocations)
        assertArrayEquals(expected.toTypedArray(), actual.await().toTypedArray())
    }

    @Test
    fun `the observer returns an empty list if a location is blank`() = runBlocking {
        val expected = emptyList<CityLocation>()
        val actual = async { repository.observeLocations()
            .filter { it is Results.Completed }
            .map { it.takeCompleted()!! }
            .first()
        }
        repository.searchLocations("    ")
        assertEquals(expected, actual.await())
    }
}