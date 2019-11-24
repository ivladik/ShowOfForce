package com.example.feature_tours.util

import androidx.recyclerview.widget.DiffUtil
import com.example.feature_tours.domain.model.Tour

class ToursDiffUtilCallback(private val oldTours: List<Tour>, private val newTours: List<Tour>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTours[oldItemPosition].hotelName == newTours[newItemPosition].hotelName
    }

    override fun getOldListSize(): Int {
        return oldTours.size
    }

    override fun getNewListSize(): Int {
        return newTours.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTours[oldItemPosition] == newTours[newItemPosition]
    }
}