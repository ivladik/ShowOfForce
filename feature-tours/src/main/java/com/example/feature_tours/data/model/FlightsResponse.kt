package com.example.feature_tours.data.model

import com.google.gson.annotations.SerializedName

data class FlightsResponse(
    @SerializedName("flights")
    val flights: List<Flight>
)