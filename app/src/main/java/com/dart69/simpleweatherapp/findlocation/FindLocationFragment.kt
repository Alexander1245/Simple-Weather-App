package com.dart69.simpleweatherapp.findlocation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dart69.mvvm_core.coroutines.collectWithLifecycle
import com.dart69.mvvm_core.presentation.screens.BaseFragment
import com.dart69.simpleweatherapp.R
import com.dart69.simpleweatherapp.core.MarginItemDecoration
import com.dart69.simpleweatherapp.core.OpenScreenEvent
import com.dart69.simpleweatherapp.databinding.FragmentFindLocationBinding
import dagger.hilt.android.AndroidEntryPoint

class OnLocationQueryListener(
    private val viewModel: FindLocationViewModel
) : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean = false

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let(viewModel::searchLocation)
        return true
    }
}

@AndroidEntryPoint
class FindLocationFragment :
    BaseFragment<FragmentFindLocationBinding, FindLocationViewModel>(R.layout.fragment_find_location) {
    override val viewBinding by viewBinding(FragmentFindLocationBinding::bind)
    override val viewModel by viewModels<FindLocationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FindLocationAdapter()
        val eventHandler = FindLocationUiEventHandler(
            findNavController(),
            requireActivity().onBackPressedDispatcher,
        )

        viewModel.observeUiEvents().collectWithLifecycle(viewLifecycleOwner) {
            eventHandler.handle(it)
        }

        viewModel.observeUiStates().collectWithLifecycle(viewLifecycleOwner) {
            adapter.submitList(it.locations)
            viewBinding.textViewError.isVisible = it.isErrorVisible
            viewBinding.progressBar.isVisible = it.isProgressVisible
            viewBinding.hintTextView.isVisible = it.isHintVisible
            viewBinding.textViewError.text = it.errorMessage

            Log.d("FindLocation", it.errorMessage)
        }

        viewBinding.recyclerViewLocations.apply {
            val decoration = MarginItemDecoration()
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(decoration)
        }
        viewBinding.apply {
            searchViewLocation.setOnQueryTextListener(OnLocationQueryListener(viewModel))
            buttonCancel.setOnClickListener { viewModel.navigateUp() }
            /*chipDetermineLocation.setOnClickListener { viewModel. }*/
            chipChooseOnMap.setOnClickListener { viewModel.openMapScreen() }
        }
    }
}