package com.example.feature_tours.di.screen

import androidx.lifecycle.ViewModelProvider
import com.example.core.presentation.view_model.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ToursScreenViewModelFactoryModule {

    @Binds
    @ToursScreenScope
    internal abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}