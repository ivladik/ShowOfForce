package com.example.showofforce.di

import com.example.showofforce.di.app.AppComponent
import com.example.feature_tours.di.ToursSubcomponent
import com.example.feature_tours.di.screen.select_flights.SelectFlightSubcomponent
import com.example.feature_tours.di.screen.show_tours.ShowToursSubcomponent

object FeatureInjector {

    fun createToursSubcomponent(): ToursSubcomponent? {
        return ToursSubcomponent.create(
            AppComponent.instance
                .toursSubcomponent()
        )
    }
}