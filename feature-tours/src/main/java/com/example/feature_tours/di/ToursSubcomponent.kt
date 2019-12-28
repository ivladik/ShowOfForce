package com.example.feature_tours.di

import com.example.feature_tours.di.screen.SelectFlightSubcomponent
import com.example.feature_tours.di.screen.ShowToursSubcomponent
import com.example.feature_tours.start.IToursStarter
import dagger.Subcomponent

@ToursScope
@Subcomponent(modules = [ToursModule::class])
abstract class ToursSubcomponent {

    abstract fun starter(): IToursStarter

    abstract fun showToursSubcomponent(): ShowToursSubcomponent

    abstract fun selectFlightSubcomponent(): SelectFlightSubcomponent

    companion object {

        var instance: ToursSubcomponent? = null
            private set

        fun create(toursSubcomponent: ToursSubcomponent): ToursSubcomponent? {
            if (instance == null) {
                instance = toursSubcomponent
            }
            return instance
        }

        fun release() {
            instance = null
        }
    }
}