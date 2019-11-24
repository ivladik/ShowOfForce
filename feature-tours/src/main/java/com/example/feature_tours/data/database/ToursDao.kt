package com.example.feature_tours.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.feature_tours.data.model.Airline
import com.example.feature_tours.data.model.Flight
import com.example.feature_tours.data.model.Hotel
import com.example.feature_tours.data.model.HotelFlightJoin
import com.example.feature_tours.data.model.AvailableEntireTourDataModel
import io.reactivex.Single

@Dao
interface ToursDao {

    @Insert(onConflict = REPLACE)
    fun saveAirlines(airlines: List<Airline>)

    @Query("SELECT * FROM Airline")
    fun getAllAirlines(): Single<List<Airline>>

    @Query("DELETE FROM Airline")
    fun deleteAllAirlines()

    @Insert(onConflict = REPLACE)
    fun saveFlights(flights: List<Flight>)

    @Query("SELECT * FROM Flight")
    fun getAllFlights(): Single<List<Flight>>

    @Query("DELETE FROM Flight")
    fun deleteAllFlights()

    @Insert(onConflict = REPLACE)
    fun saveHotels(hotels: List<Hotel>)

    @Query("SELECT * FROM Hotel")
    fun getAllHotels(): Single<List<Hotel>>

    @Query("DELETE FROM Hotel")
    fun deleteAllHotels()

    @Insert(onConflict = REPLACE)
    fun saveHotelFlightJoins(joins: List<HotelFlightJoin>)

    @Query("SELECT * FROM HotelFlightJoin")
    fun getAllHotelFlightJoins(): Single<List<HotelFlightJoin>>

    @Query("DELETE FROM HotelFlightJoin")
    fun deleteAllHotelFlightJoins()

    @Insert(onConflict = REPLACE)
    fun saveAvailableEntireTours(availableEntireTours: List<AvailableEntireTourDataModel>)

    @Query("SELECT * FROM AvailableEntireTourDataModel WHERE tourId = :tourId")
    fun getAvailableEntireTourById(tourId: Int): Single<List<AvailableEntireTourDataModel>>

    @Query("DELETE FROM AvailableEntireTourDataModel")
    fun deleteAllAvailableEntireTours()
}