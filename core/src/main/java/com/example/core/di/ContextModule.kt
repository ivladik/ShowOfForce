package com.example.core.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {

    @Provides
    @PerApp
    fun provideContext(): Context {
        return context
    }
}