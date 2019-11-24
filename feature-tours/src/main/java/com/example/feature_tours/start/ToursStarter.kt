package com.example.feature_tours.start

import android.content.Context
import android.content.Intent
import com.example.feature_tours.presentation.ui.activity.ToursActivity

class ToursStarter : IToursStarter {

    override fun start(context: Context) {
        context.startActivity(
            Intent(context, ToursActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}