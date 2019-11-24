package com.example.feature_tours.domain.model

data class Tour(
    val id: Int,
    val hotelName: String,
    val availableFlightsForHotel: Int,
    val minimalPriceForEntireTour: Int
)