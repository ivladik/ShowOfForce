package com.example.feature_tours.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.feature_tours.R
import com.example.feature_tours.di.ToursSubcomponent
import com.example.feature_tours.presentation.ui.fragment.ShowToursFragment

class ToursActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_tours)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ShowToursFragment.newInstance(), ShowToursFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        if (isFinishing) {
            ToursSubcomponent.release()
        }
        super.onDestroy()
    }
}
