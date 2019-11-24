package com.example.feature_tours.data.data_source.server

import com.example.feature_tours.data.model.*
import io.reactivex.Single

interface IServerToursDataSource {

    fun getFlights(): Single<FlightsResponse>

    fun getHotels(): Single<HotelsResponse>

    fun getAirlines(): Single<AirlinesResponse>
}