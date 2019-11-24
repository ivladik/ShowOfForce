package com.example.feature_tours.domain.interactor

import com.example.feature_tours.data.repository.IToursRepository
import com.example.feature_tours.domain.model.AvailableEntireTourDomainModel
import com.example.feature_tours.domain.model.Tour
import io.reactivex.Single

class ToursInteractor(private val repository: IToursRepository) : IToursInteractor {

    override fun loadTours(): Single<List<Tour>> {
        return repository.loadTours()
    }

    override fun loadAvailableEntireToursById(tourId: Int): Single<List<AvailableEntireTourDomainModel>> {
        return repository.getAvailableEntireToursById(tourId)
    }
}