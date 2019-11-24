package com.example.feature_tours.data.repository

import com.example.feature_tours.data.data_source.database.IDatabaseToursDataSource
import com.example.feature_tours.data.data_source.server.IServerToursDataSource
import com.example.feature_tours.data.mapper.AvailableEntireToursModelMapper
import com.example.feature_tours.data.mapper.BasisForToursModelMapper
import com.example.feature_tours.data.mapper.HotelFlightJoinModelMapper
import com.example.feature_tours.data.mapper.TourModelMapper
import com.example.feature_tours.data.model.*
import com.example.feature_tours.data.model.BasisForTours
import com.example.feature_tours.domain.model.AvailableEntireTourDomainModel
import com.example.feature_tours.domain.model.Tour
import io.reactivex.Single
import io.reactivex.functions.Function4
import timber.log.Timber

class ToursRepository(
    private val serverToursDataSource: IServerToursDataSource,
    private val databaseToursDataSource: IDatabaseToursDataSource
) : IToursRepository {

    override fun loadTours(): Single<List<Tour>> {
        return Single.zip<List<Flight>, List<Hotel>, List<Airline>, List<HotelFlightJoin>, List<BasisForTours>>(
            getFlights(),
            getHotels(),
            getAirlines(),
            getHotelFlightJoinsFromDatabase(),
            // TODO: error handling
            Function4 { flights, hotels, airlines, hotelFlightJoins ->
                BasisForToursModelMapper.map(flights, hotels, airlines, hotelFlightJoins)
            }
        )
            .doOnSuccess { basisForTours ->
                    saveAvailableEntireTours(AvailableEntireToursModelMapper.map(basisForTours))
            }
            .map {
                TourModelMapper.map(it)
            }
    }

    private fun getFlights(): Single<List<Flight>> {
        return serverToursDataSource.getFlights()
            .map { it.flights }
            .doOnSuccess {
                    databaseToursDataSource.deleteFlights()
                    databaseToursDataSource.saveFlights(it)
            }
            .onErrorResumeNext {
                Timber.e(it)
                databaseToursDataSource.getFlights()
            }
    }

    private fun getHotels(): Single<List<Hotel>> {
        return serverToursDataSource.getHotels()
            .map { it.hotels }
            .doOnSuccess {
                    with(databaseToursDataSource) {
                        deleteHotels()
                        saveHotels(it)
                        deleteHotelFlightJoins()
                        saveHotelFlightJoins(HotelFlightJoinModelMapper.map(it))
                    }
            }
            .onErrorResumeNext {
                Timber.e(it)
                databaseToursDataSource.getHotels()
            }
    }

    private fun getAirlines(): Single<List<Airline>> {
        return serverToursDataSource.getAirlines()
            .map { it.airlines }
            .doOnSuccess {
                    databaseToursDataSource.deleteAirlines()
                    databaseToursDataSource.saveAirlines(it)
            }
            .onErrorResumeNext {
                Timber.e(it)
                databaseToursDataSource.getAirlines()
            }
    }

    private fun getHotelFlightJoinsFromDatabase(): Single<List<HotelFlightJoin>> {
        return databaseToursDataSource.getHotelFlightJoins()
    }

    private fun saveAvailableEntireTours(availableEntireTours: List<AvailableEntireTourDataModel>) {
        databaseToursDataSource.deleteAvailableEntireTours()
        databaseToursDataSource.saveAvailableEntireTours(availableEntireTours)
    }

    override fun getAvailableEntireToursById(tourId: Int): Single<List<AvailableEntireTourDomainModel>> {
        return databaseToursDataSource.getAvailableEntireToursById(tourId)
            .map { AvailableEntireToursModelMapper.mapToDomainModel(it) }
    }
}