package com.example.showofforce.di

import com.example.feature_movies.di.MoviesSubcomponent
import com.example.showofforce.di.app.AppComponent
import com.example.feature_tours.di.ToursSubcomponent

object FeatureInjector {

    fun createToursSubcomponent(): ToursSubcomponent? {
        return ToursSubcomponent.create(
            AppComponent.instance
                .toursSubcomponent()
        )
    }

    fun createMoviesSubcomponent(): MoviesSubcomponent? {
        return MoviesSubcomponent.create(
            AppComponent.instance
                .moviesSubcomponent()
        )
    }
}