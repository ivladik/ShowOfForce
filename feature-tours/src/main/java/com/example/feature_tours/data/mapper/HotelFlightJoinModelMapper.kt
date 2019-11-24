package com.example.feature_tours.data.mapper

import com.example.feature_tours.data.model.Hotel
import com.example.feature_tours.data.model.HotelFlightJoin

object HotelFlightJoinModelMapper {

    // TODO: delete 2x for
    fun map(hotels: List<Hotel>): List<HotelFlightJoin> {
        val joins = arrayListOf<HotelFlightJoin>()
        hotels.forEach { hotel ->
            val hotelId = hotel.id
            hotel.flightsIds.forEach { flightId ->
                joins.add(HotelFlightJoin(hotelId, flightId))
            }
        }
        return joins
    }
}