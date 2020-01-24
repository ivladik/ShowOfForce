package com.example.feature_movies.di

import com.example.feature_movies.presentation.ui.fragment.MoviesStubFragment
import dagger.Subcomponent

@MoviesScope
@Subcomponent(modules = [MoviesModule::class, MoviesScreenViewModelFactoryModule::class])
abstract class MoviesSubcomponent { // TODO: one more subcomponent

    abstract fun injectMoviesStubFragment(moviesStubFragment: MoviesStubFragment)

    companion object {

        var instance: MoviesSubcomponent? = null
            private set

        fun create(moviesSubcomponent: MoviesSubcomponent?): MoviesSubcomponent? {
            if (instance == null) {
                instance = moviesSubcomponent
            }

            return instance
        }

        fun release() {
            instance = null
        }
    }
}