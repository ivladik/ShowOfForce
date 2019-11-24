package com.example.feature_tours.data.mapper

import com.example.feature_tours.data.model.*

object BasisForToursModelMapper {

    fun map(
        flights: List<Flight>,
        hotels: List<Hotel>,
        airlines: List<Airline>,
        hotelFlightJoins: List<HotelFlightJoin>
    ): List<BasisForTours> {
        return hotels.map { hotel ->
            // flights
            val availableFlightsIdsForHotel = hotelFlightJoins.filter { it.hotelId == hotel.id }
                .map { it.flightId }
            val flightsRelatedToHotel =
                flights.filter { availableFlightsIdsForHotel.contains(it.id) }
            // airlines
            val airlinesIdsRelatedToFlights = flightsRelatedToHotel.map { it.airlineId }
            val airlinesRelatedToFlights =
                airlines.filter { airlinesIdsRelatedToFlights.contains(it.id) }
            BasisForTours(
                hotel,
                flightsRelatedToHotel,
                airlinesRelatedToFlights
            )
        }
    }
}