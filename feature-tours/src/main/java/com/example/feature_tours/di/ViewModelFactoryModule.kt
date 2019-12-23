package com.example.feature_tours.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_tours.di.screen.ToursScreenScope
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
    // TODO: ужасно! заменить на ShowToursScope - перенести фабрику или только вью модель?
    @ToursScope
    @IntoMap
    @ViewModelKey(ShowToursViewModel::class)
    internal abstract fun provideShowToursViewModel(
        viewModel: ShowToursViewModel
    ): ViewModel
}