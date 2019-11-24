package com.example.feature_tours.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.feature_tours.data.model.*

private const val TOURS_DATABASE_NAME = "tours.db"

@Database(
    entities = [
        Airline::class,
        Flight::class,
        Hotel::class,
        HotelFlightJoin::class,
        AvailableEntireTourDataModel::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ToursDatabase : RoomDatabase() {

    abstract fun getDao(): ToursDao

    companion object {
        private var instance: ToursDatabase? = null
        fun getDatabase(context: Context): ToursDatabase {
            return instance
                ?: Room.databaseBuilder(
                    context,
                    ToursDatabase::class.java,
                    TOURS_DATABASE_NAME
                )
                    .build()
        }
    }
}