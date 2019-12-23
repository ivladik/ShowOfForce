package com.example.feature_tours.di.screen.show_tours

import androidx.lifecycle.ViewModel
import com.example.feature_tours.di.ToursScope
import com.example.feature_tours.di.ViewModelKey
import com.example.feature_tours.di.screen.ToursScreenScope
import com.example.feature_tours.domain.interactor.IToursInteractor
import com.example.feature_tours.presentation.view_model.ShowToursViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class ShowToursModule {

    /*@Binds
    // TODO: ужасно! заменить на ShowToursScope - перенести фабрику или только вью модель?
    @ToursScope
    @IntoMap
    @ViewModelKey(ShowToursViewModel::class)
    internal abstract fun provideShowToursViewModel(
        viewModel: ShowToursViewModel
    ): ViewModel*/
}