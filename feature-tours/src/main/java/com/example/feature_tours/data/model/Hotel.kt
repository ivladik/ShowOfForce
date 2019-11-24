package com.example.feature_tours.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Hotel(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
) {

    @Ignore
    @SerializedName("flights")
    val flightsIds: List<Int> = arrayListOf()
}