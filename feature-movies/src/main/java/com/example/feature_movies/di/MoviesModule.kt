package com.example.feature_movies.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.feature_movies.presentation.view_model.MoviesStubViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MoviesModule {

    @Binds
    @MoviesScope
    @IntoMap
    @ViewModelKey(MoviesStubViewModel::class)
    internal abstract fun provideMoviesStubViewModel(viewModel: MoviesStubViewModel): ViewModel
}