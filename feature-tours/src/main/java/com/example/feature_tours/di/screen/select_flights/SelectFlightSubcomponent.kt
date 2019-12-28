package com.example.feature_tours.di.screen.select_flights

import com.example.feature_tours.di.screen.ViewModelFactoryModule
import com.example.feature_tours.di.screen.ToursScreenScope
import com.example.feature_tours.presentation.ui.dialog.SelectFlightDialogFragment
import dagger.Subcomponent

@ToursScreenScope
@Subcomponent(modules = [SelectFlightModule::class, ViewModelFactoryModule::class])
abstract class SelectFlightSubcomponent {

    abstract fun injectSelectFlightDialogFragment(selectFlightDialogFragment: SelectFlightDialogFragment)

    companion object {

        var instance: SelectFlightSubcomponent? = null
            private set

        fun create(selectFlightSubcomponent: SelectFlightSubcomponent?): SelectFlightSubcomponent? {
            if (instance == null) {
                instance = selectFlightSubcomponent
            }
            return instance
        }

        fun release() {
            instance = null
        }
    }
}