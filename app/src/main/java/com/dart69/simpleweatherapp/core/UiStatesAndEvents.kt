package com.dart69.simpleweatherapp.core

import androidx.navigation.NavDirections
import com.dart69.mvvm_core.presentation.viewmodels.UiEvent

data class OpenScreenEvent(val directions: NavDirections): UiEvent

object NavigateUpEvent : UiEvent