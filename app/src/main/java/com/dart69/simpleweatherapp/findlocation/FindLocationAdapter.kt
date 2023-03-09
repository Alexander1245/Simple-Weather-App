package com.dart69.simpleweatherapp.findlocation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dart69.domain.models.CityLocation
import com.dart69.simpleweatherapp.databinding.LocationItemBinding

class CityLocationItemCallback : DiffUtil.ItemCallback<CityLocation>() {
    override fun areItemsTheSame(oldItem: CityLocation, newItem: CityLocation): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: CityLocation, newItem: CityLocation): Boolean =
        oldItem == newItem
}

class FindLocationAdapter : ListAdapter<CityLocation, FindLocationViewHolder>(CityLocationItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindLocationViewHolder =
        FindLocationViewHolder(
            LocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: FindLocationViewHolder, position: Int) =
        holder.bind(currentList[position])
}

class FindLocationViewHolder(
    private val binding: LocationItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CityLocation) {
        binding.run {
            textViewCity.text = item.name
            textViewCountry.text = item.country
            textViewState.text = item.state
        }
    }
}