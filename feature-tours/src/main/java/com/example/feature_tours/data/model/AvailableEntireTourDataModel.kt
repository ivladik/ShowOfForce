package com.example.feature_tours.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AvailableEntireTourDataModel(
    @PrimaryKey
    val id: Int,
    val tourId: Int,
    val airlineName: String,
    val price: Int
)