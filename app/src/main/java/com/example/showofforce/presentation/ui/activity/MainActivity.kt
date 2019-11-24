package com.example.showofforce.presentation.ui.activity

import android.os.Bundle
import com.example.showofforce.R
import com.example.showofforce.di.FeatureInjector
import moxy.MvpAppCompatActivity

// TODO: tests, jacoco
// TODO: add icons
class MainActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main)
        navigateToToursFragment()
    }

    // TODO: Single Activity, Navigation Component
    private fun navigateToToursFragment() {
        FeatureInjector.createToursSubcomponent()
            ?.starter()
            ?.start(this)
    }
}
