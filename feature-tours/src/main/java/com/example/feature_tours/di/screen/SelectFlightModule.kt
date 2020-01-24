package com.example.feature_tours.di.screen

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.feature_tours.presentation.view_model.SelectFlightViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SelectFlightModule {

    @Binds
    @ToursScreenScope
    @IntoMap
    @ViewModelKey(SelectFlightViewModel::class)
    internal abstract fun provideSelectFlightViewModel(
        viewModel: SelectFlightViewModel
    ): ViewModel
}