package com.example.showofforce.di.app

import android.content.Context
import com.example.core.di.ContextModule
import com.example.core.di.NetworkModule
import com.example.core.di.PerApp
import com.example.feature_tours.di.ToursSubcomponent
import dagger.Component

@PerApp
@Component(modules = [NetworkModule::class, ContextModule::class])
abstract class AppComponent {

    abstract fun toursSubcomponent(): ToursSubcomponent

    companion object {

        lateinit var instance: AppComponent private set

        fun create(context: Context) {
            instance = DaggerAppComponent.builder()
                .contextModule(ContextModule(context))
                .build()
        }
    }
}