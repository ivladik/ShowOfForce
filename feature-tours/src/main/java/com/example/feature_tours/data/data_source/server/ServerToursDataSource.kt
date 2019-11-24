package com.example.feature_tours.data.data_source.server

import com.example.feature_tours.data.server.ToursService
import com.example.feature_tours.data.model.*
import io.reactivex.Single

class ServerToursDataSource(private val toursService: ToursService) :
    IServerToursDataSource {

    override fun getFlights(): Single<FlightsResponse> {
        return toursService.getFlights()
    }

    override fun getHotels(): Single<HotelsResponse> {
        return toursService.getHotels()
    }

    override fun getAirlines(): Single<AirlinesResponse> {
        return toursService.getAirlines()
    }
}