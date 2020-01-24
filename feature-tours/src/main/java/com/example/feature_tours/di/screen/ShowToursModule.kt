package com.example.feature_tours.di.screen

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.feature_tours.presentation.view_model.ShowToursViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ShowToursModule {

    @Binds
    @ToursScreenScope
    @IntoMap
    @ViewModelKey(ShowToursViewModel::class)
    internal abstract fun provideShowToursViewModel(
        viewModel: ShowToursViewModel
    ): ViewModel
}