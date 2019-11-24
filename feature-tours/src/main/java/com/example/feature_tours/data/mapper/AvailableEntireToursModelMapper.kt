package com.example.feature_tours.data.mapper

import com.example.feature_tours.data.model.AvailableEntireTourDataModel
import com.example.feature_tours.data.model.BasisForTours
import com.example.feature_tours.domain.model.AvailableEntireTourDomainModel

object AvailableEntireToursModelMapper {

    fun map(
        basisForTours: List<BasisForTours>
    ): List<AvailableEntireTourDataModel> {
        val availableEntireTours = arrayListOf<AvailableEntireTourDataModel>()
        var availableEntireTourId = 0
        basisForTours.forEach { loadTour ->
            loadTour.flightsRelatedToHotel.forEach { flight ->
                val priceForEntireTour = loadTour.hotel.price + flight.price
                val airlineName =
                    loadTour.airlinesRelatedToFlights.find { flight.airlineId == it.id }
                        ?.name
                        ?: ""
                availableEntireTours.add(
                    AvailableEntireTourDataModel(
                        availableEntireTourId++,
                        loadTour.hotel.id,
                        airlineName,
                        priceForEntireTour
                    )
                )
            }
        }
        return availableEntireTours
    }

    fun mapToDomainModel(
        availableEntireTourDataModels: List<AvailableEntireTourDataModel>
    ): List<AvailableEntireTourDomainModel> {
        return availableEntireTourDataModels.map {
            AvailableEntireTourDomainModel(
                it.tourId,
                it.airlineName,
                it.price
            )
        }
    }
}