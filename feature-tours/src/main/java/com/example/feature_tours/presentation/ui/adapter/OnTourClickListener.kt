package com.example.feature_tours.presentation.ui.adapter

import com.example.feature_tours.domain.model.Tour

interface OnTourClickListener {

    fun onTourClick(tour: Tour)
}