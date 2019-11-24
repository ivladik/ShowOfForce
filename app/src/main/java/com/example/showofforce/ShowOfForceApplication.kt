package com.example.showofforce

import android.app.Application
import com.example.showofforce.di.app.AppComponent
import com.facebook.stetho.Stetho
import timber.log.Timber

class ShowOfForceApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            Timber.plant(Timber.DebugTree())
        }
        AppComponent.create(this)
    }
}