package com.dart69.simpleweatherapp.findlocation

import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.NavController
import com.dart69.mvvm_core.presentation.viewmodels.UiEvent
import com.dart69.simpleweatherapp.core.NavigateUpEvent
import com.dart69.simpleweatherapp.core.OpenScreenEvent
import com.dart69.simpleweatherapp.core.UiEventHandler

class FindLocationUiEventHandler(
    private val navController: NavController,
    private val onBackPressedDispatcher: OnBackPressedDispatcher
) : UiEventHandler {
    override fun handle(event: UiEvent) {
        when(event) {
            is NavigateUpEvent -> onBackPressedDispatcher.onBackPressed()
            is OpenScreenEvent -> navController.navigate(event.directions)
        }
    }
}