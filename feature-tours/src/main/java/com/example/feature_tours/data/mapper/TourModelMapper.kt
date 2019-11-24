package com.example.feature_tours.data.mapper

import com.example.feature_tours.data.model.Flight
import com.example.feature_tours.data.model.Hotel
import com.example.feature_tours.data.model.BasisForTours
import com.example.feature_tours.domain.model.Tour

object TourModelMapper {

    fun map(basisForTours: List<BasisForTours>): List<Tour> {
        return basisForTours.map { loadTour ->
            // entire tour's minimal price
            val minimalPriceForEntireTour =
                resolveMinimalPriceForEntireTour(
                    loadTour.hotel,
                    loadTour.flightsRelatedToHotel
                )
            Tour(
                loadTour.hotel.id,
                loadTour.hotel.name,
                loadTour.flightsRelatedToHotel.size,
                minimalPriceForEntireTour
            )
        }
    }

    private fun resolveMinimalPriceForEntireTour(
        hotel: Hotel,
        flightsRelatedToHotel: List<Flight>
    ): Int {
        val flightWithMinimalPrice = flightsRelatedToHotel.minBy { it.price }
            ?.price
            ?: 0
        return hotel.price + flightWithMinimalPrice
    }
}