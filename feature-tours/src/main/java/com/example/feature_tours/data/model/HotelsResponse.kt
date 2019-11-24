package com.example.feature_tours.data.model

import com.google.gson.annotations.SerializedName

data class HotelsResponse(
    @SerializedName("hotels")
    val hotels: List<Hotel>
)