package com.example.feature_tours.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Flight(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("companyId")
    val airlineId: Int,
    @SerializedName("departure")
    val departure: String,
    @SerializedName("arrival")
    val arrival: String,
    @SerializedName("price")
    val price: Int
)