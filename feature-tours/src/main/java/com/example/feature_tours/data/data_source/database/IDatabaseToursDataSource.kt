package com.example.feature_tours.data.data_source.database

import com.example.feature_tours.data.model.Airline
import com.example.feature_tours.data.model.Flight
import com.example.feature_tours.data.model.Hotel
import com.example.feature_tours.data.model.HotelFlightJoin
import com.example.feature_tours.data.model.AvailableEntireTourDataModel
import io.reactivex.Single

interface IDatabaseToursDataSource {

    fun getFlights(): Single<List<Flight>>

    fun saveFlights(flights: List<Flight>)

    fun deleteFlights()

    fun getHotels(): Single<List<Hotel>>

    fun saveHotels(hotels: List<Hotel>)

    fun deleteHotels()

    fun getAirlines(): Single<List<Airline>>

    fun saveAirlines(airlines: List<Airline>)

    fun deleteAirlines()

    fun getHotelFlightJoins(): Single<List<HotelFlightJoin>>

    fun saveHotelFlightJoins(joins: List<HotelFlightJoin>)

    fun deleteHotelFlightJoins()

    fun getAvailableEntireToursById(tourId: Int): Single<List<AvailableEntireTourDataModel>>

    fun saveAvailableEntireTours(availableEntireTours: List<AvailableEntireTourDataModel>)

    fun deleteAvailableEntireTours()
}