package com.example.feature_tours.data.data_source.database

import com.example.feature_tours.data.database.ToursDao
import com.example.feature_tours.data.model.*
import com.example.feature_tours.data.model.AvailableEntireTourDataModel
import io.reactivex.Single

class DatabaseToursDataSource(private val toursDao: ToursDao) :
    IDatabaseToursDataSource {

    override fun getFlights(): Single<List<Flight>> {
        return toursDao.getAllFlights()
    }

    override fun saveFlights(flights: List<Flight>) {
        toursDao.saveFlights(flights)
    }

    override fun deleteFlights() {
        toursDao.deleteAllFlights()
    }

    override fun getHotels(): Single<List<Hotel>> {
        return toursDao.getAllHotels()
    }

    override fun saveHotels(hotels: List<Hotel>) {
        toursDao.saveHotels(hotels)
    }

    override fun deleteHotels() {
        toursDao.deleteAllHotels()
    }

    override fun getAirlines(): Single<List<Airline>> {
        return toursDao.getAllAirlines()
    }

    override fun saveAirlines(airlines: List<Airline>) {
        toursDao.saveAirlines(airlines)
    }

    override fun deleteAirlines() {
        toursDao.deleteAllAirlines()
    }

    override fun getHotelFlightJoins(): Single<List<HotelFlightJoin>> {
        return toursDao.getAllHotelFlightJoins()
    }

    override fun saveHotelFlightJoins(joins: List<HotelFlightJoin>) {
        toursDao.saveHotelFlightJoins(joins)
    }

    override fun deleteHotelFlightJoins() {
        toursDao.deleteAllHotelFlightJoins()
    }

    override fun getAvailableEntireToursById(tourId: Int): Single<List<AvailableEntireTourDataModel>> {
        return toursDao.getAvailableEntireTourById(tourId)
    }

    override fun saveAvailableEntireTours(availableEntireTours: List<AvailableEntireTourDataModel>) {
        toursDao.saveAvailableEntireTours(availableEntireTours)
    }

    override fun deleteAvailableEntireTours() {
        toursDao.deleteAllAvailableEntireTours()
    }
}