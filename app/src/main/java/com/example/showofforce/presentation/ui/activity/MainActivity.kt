package com.example.showofforce.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.feature_movies.di.MoviesSubcomponent
import com.example.feature_tours.di.ToursSubcomponent
import com.example.showofforce.R
import com.example.showofforce.di.FeatureInjector
import kotlinx.android.synthetic.main.ac_main.*

// TODO: ЛИБО ВСЮ НАВИГАЦИЮ ПЕРЕНЕСТИ В МОДУЛЬ, ЛИБО ВЕРНУТЬ ВСЕ В АПП. ОТДЕЛЬНЫЙ МОДУЛЬ НАВИГАЦИЯ СОЗДАТЬ?
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main)
        setupNavigation()
//        navigateToToursFragment()
        FeatureInjector.createToursSubcomponent()
        FeatureInjector.createMoviesSubcomponent()
    }

    private fun setupNavigation() {
        val navigationController = findNavController(R.id.navigationController)
        setupActionBarWithNavController(
            navigationController,
            AppBarConfiguration.Builder(
                setOf(
                    R.id.itemTours,
                    R.id.itemMovies
                )
            )
                .build()
        )
        navigationView.setupWithNavController(navigationController)
    }

    override fun onDestroy() {
        if (isFinishing) {
            MoviesSubcomponent.release()
            ToursSubcomponent.release()
        }
        super.onDestroy()
    }

    // TODO: удалить starter, activity или наоборот запускать именно starter'ы
    private fun navigateToToursFragment() {
        FeatureInjector.createToursSubcomponent()
            ?.starter()
            ?.start(this)
    }

    override fun onSupportNavigateUp(): Boolean = findNavController(R.id.navigationController).navigateUp()
}
