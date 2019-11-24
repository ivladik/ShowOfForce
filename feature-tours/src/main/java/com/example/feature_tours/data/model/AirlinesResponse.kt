package com.example.feature_tours.data.model

import com.google.gson.annotations.SerializedName

data class AirlinesResponse(
    @SerializedName("companies")
    val airlines: List<Airline>
)