package com.example.feature_tours.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = arrayOf("hotelId", "flightId"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Hotel::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("hotelId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Flight::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("flightId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class HotelFlightJoin(
    val hotelId: Int,
    val flightId: Int
)