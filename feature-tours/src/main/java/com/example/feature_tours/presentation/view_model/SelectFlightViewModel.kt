package com.example.feature_tours.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.presentation.view_model.BaseViewModel
import com.example.core.util.RxSchedulersUtil
import com.example.feature_tours.di.screen.SelectFlightSubcomponent
import com.example.feature_tours.domain.interactor.IToursInteractor
import com.example.feature_tours.domain.model.AvailableEntireTourDomainModel
import com.example.feature_tours.presentation.model.Response
import timber.log.Timber
import javax.inject.Inject

class SelectFlightViewModel @Inject constructor(private val interactor: IToursInteractor) : BaseViewModel() {

    private val responseLiveData = MutableLiveData<Response<List<AvailableEntireTourDomainModel>>>()

    fun getAvailableEntireTours(tourId: Int): LiveData<Response<List<AvailableEntireTourDomainModel>>> {
        if (responseLiveData.value == null) {
            loadAvailableEntireTours(tourId)
        }
        return responseLiveData
    }

    private fun loadAvailableEntireTours(tourId: Int) {
        interactor.loadAvailableEntireToursById(tourId)
            .compose(RxSchedulersUtil.getIOToMainSingle())
            .subscribe(
                {
                    responseLiveData.value = Response.Done(it)
                },
                {
                    // TODO: error handling
                    Timber.e(it)
                }
            )
            .untilCleared()
    }

    override fun onCleared() {
        SelectFlightSubcomponent
            .release()
        super.onCleared()
    }
}