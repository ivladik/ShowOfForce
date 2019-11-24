package com.example.feature_tours.domain.interactor

import com.example.feature_tours.domain.model.AvailableEntireTourDomainModel
import com.example.feature_tours.domain.model.Tour
import io.reactivex.Single

interface IToursInteractor {

    fun loadTours(): Single<List<Tour>>

    fun loadAvailableEntireToursById(tourId: Int): Single<List<AvailableEntireTourDomainModel>>
}