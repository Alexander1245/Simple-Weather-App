package com.dart69.simpleweatherapp.findlocation

import androidx.lifecycle.viewModelScope
import com.dart69.coroutines.context.AvailableDispatchers
import com.dart69.coroutines.results.Results
import com.dart69.coroutines.results.takeCompleted
import com.dart69.domain.LocationRepository
import com.dart69.domain.models.CityLocation
import com.dart69.mvvm_core.presentation.viewmodels.*
import com.dart69.simpleweatherapp.core.NavigateUpEvent
import com.dart69.simpleweatherapp.core.OpenScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FindLocationUiState(
    val locations: List<CityLocation>,
    val isProgressVisible: Boolean,
    val isHintVisible: Boolean,
    val isErrorVisible: Boolean,
    val errorMessage: String,
) : UiState {
    companion object {
        val INITIAL = FindLocationUiState(
            locations = emptyList(),
            isProgressVisible = false,
            isHintVisible = true,
            isErrorVisible = false,
            errorMessage = "",
        )
    }
}

//TODO: Handle unknown host error

@HiltViewModel
class FindLocationViewModel @Inject constructor(
    private val dispatchers: AvailableDispatchers,
    private val repository: LocationRepository,
) : CommunicatorViewModel<FindLocationUiState, UiEvent>(
    uiStates = MutableUiStateObserver.Default(FindLocationUiState.INITIAL),
    uiEvents = MutableUiEventObserver.ChannelObserver(),
) {

    init {
        viewModelScope.launch(dispatchers.default) {
            repository.observeLocations().collect { results ->
                uiStates.updateUiState { uiState ->
                    val locations = results.takeCompleted() ?: emptyList()
                    val errorMessage = (results as? Results.Error)?.throwable?.message.orEmpty()
                    uiState.copy(
                        locations = locations,
                        isProgressVisible = results is Results.Loading,
                        isHintVisible = locations.isEmpty(),
                        isErrorVisible = results is Results.Error,
                        errorMessage = errorMessage,
                    )
                }
            }
        }
    }

    fun searchLocation(location: String) {
        repository.searchLocations(location.trim().lowercase())
    }

    fun openMapScreen() {
        val direction = FindLocationFragmentDirections.actionFindLocationFragmentToMapFragment()
        uiEvents.launchUiEvent(OpenScreenEvent(direction), dispatchers.main)
    }

    fun navigateUp() {
        uiEvents.launchUiEvent(NavigateUpEvent, dispatchers.main)
    }
}