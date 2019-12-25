package com.example.showofforce.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.showofforce.R
import com.example.showofforce.di.FeatureInjector

// TODO: tests, jacoco
// TODO: add icons
// TODO: rotate screen fix
class MainActivity : AppCompatActivity() {

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
