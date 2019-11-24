package com.example.feature_tours.di.screen.show_tours

import com.example.feature_tours.di.screen.ToursScreenScope
import com.example.feature_tours.presentation.ui.fragment.ShowToursFragment
import dagger.Subcomponent

@ToursScreenScope
@Subcomponent(modules = [ShowToursModule::class])
abstract class ShowToursSubcomponent {

    abstract fun injectShowToursFragment(showToursFragment: ShowToursFragment)

    companion object {

        var instance: ShowToursSubcomponent? = null
            private set

        fun create(showToursSubcomponent: ShowToursSubcomponent?): ShowToursSubcomponent? {
            if (instance == null) {
                instance = showToursSubcomponent
            }
            return instance
        }

        fun release() {
            instance = null
        }
    }
}