package com.example.feature_tours.data.repository

import com.example.feature_tours.domain.model.AvailableEntireTourDomainModel
import com.example.feature_tours.domain.model.Tour
import io.reactivex.Single

interface IToursRepository {

    fun loadTours(): Single<List<Tour>>

    fun getAvailableEntireToursById(tourId: Int): Single<List<AvailableEntireTourDomainModel>>
}