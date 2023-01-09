package com.amonteiro.a22_ynova

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amonteiro.a22_ynova.databinding.RowWeatherBinding

class WeatherListAdapter : ListAdapter<CoordBean, WeatherListAdapter.ViewHolder>(Comparator()) {

    class ViewHolder(val binding: RowWeatherBinding) : RecyclerView.ViewHolder(binding.root)

    class Comparator : DiffUtil.ItemCallback<CoordBean>() {

        override fun areItemsTheSame(oldItem: CoordBean, newItem: CoordBean) = oldItem === newItem

        override fun areContentsTheSame(oldItem: CoordBean, newItem: CoordBean) = oldItem == newItem

    }

    //Création des lignes
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(RowWeatherBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.binding.tvville.text = "${currentItem.lat}, ${currentItem.long}"
    }

}