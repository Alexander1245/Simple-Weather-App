package com.dart69.simpleweatherapp.core

import com.dart69.mvvm_core.presentation.viewmodels.UiEvent

interface UiEventHandler {
    fun handle(event: UiEvent)
}