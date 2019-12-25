package com.example.feature_tours.presentation.view_model

import androidx.lifecycle.MutableLiveData
import com.example.core.presentation.view_model.BaseViewModel
import com.example.core.util.RxSchedulersUtil
import com.example.feature_tours.domain.interactor.IToursInteractor
import com.example.feature_tours.domain.model.AvailableEntireTourDomainModel
import com.example.feature_tours.presentation.model.Response
import timber.log.Timber
import javax.inject.Inject

class SelectFlightViewModel @Inject constructor(private val interactor: IToursInteractor) : BaseViewModel() {

    val responseLiveData = MutableLiveData<Response<List<AvailableEntireTourDomainModel>>>()

    fun loadAvailableEntireTours(tourId: Int) {
        interactor.loadAvailableEntireToursById(tourId)
            .compose(RxSchedulersUtil.getIOToMainSingle())
            .subscribe(
                {
                    responseLiveData.value = Response.createDoneInstance(it)
                },
                {
                    // TODO: error handling
                    Timber.e(it)
                }
            )
            .untilCleared()
    }
}