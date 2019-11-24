package com.example.feature_tours.data.model

data class BasisForTours(
    val hotel: Hotel,
    val flightsRelatedToHotel: List<Flight>,
    val airlinesRelatedToFlights: List<Airline>
)