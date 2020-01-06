package com.example.feature_tours.presentation.ui.adapter

import com.example.feature_tours.domain.model.AvailableEntireTourDomainModel
import com.example.feature_tours.presentation.model.Response

interface OnEntireTourAppliedListener {

    fun onEntireTourApplied(result: Response<AvailableEntireTourDomainModel?>)
}