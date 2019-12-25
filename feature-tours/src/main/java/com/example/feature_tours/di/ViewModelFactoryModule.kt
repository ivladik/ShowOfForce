package com.example.feature_tours.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_tours.presentation.view_model.SelectFlightViewModel
import com.example.feature_tours.presentation.view_model.ShowToursViewModel
import com.example.feature_tours.presentation.view_model.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    @ToursScope
    internal abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    // TODO: change scope to ToursScreenScope and think about replace it to package show tours?
    @ToursScope
    @IntoMap
    @ViewModelKey(ShowToursViewModel::class)
    internal abstract fun provideShowToursViewModel(
        viewModel: ShowToursViewModel
    ): ViewModel

    @Binds
    // TODO: change scope to ToursScreenScope and think about replace it to package select flights?
    @ToursScope
    @IntoMap
    @ViewModelKey(SelectFlightViewModel::class)
    internal abstract fun provideSelectFlightViewModel(
        viewModel: SelectFlightViewModel
    ): ViewModel
}