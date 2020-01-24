package com.example.feature_movies.di

import androidx.lifecycle.ViewModelProvider
import com.example.core.presentation.view_model.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class MoviesScreenViewModelFactoryModule {

    @Binds
    @MoviesScope
    internal abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}