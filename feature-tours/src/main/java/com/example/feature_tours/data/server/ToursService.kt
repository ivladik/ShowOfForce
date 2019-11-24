package com.example.feature_tours.data.server

import com.example.feature_tours.data.model.AirlinesResponse
import com.example.feature_tours.data.model.FlightsResponse
import com.example.feature_tours.data.model.HotelsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ToursService {

    @GET("/bins/zqxvw")
    fun getFlights(): Single<FlightsResponse>

    @GET("/bins/12q3ws")
    fun getHotels(): Single<HotelsResponse>

    @GET("/bins/8d024")
    fun getAirlines(): Single<AirlinesResponse>
}