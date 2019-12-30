package com.example.feature_tours.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.presentation.view_model.BaseViewModel
import com.example.core.util.RxSchedulersUtil
import com.example.feature_tours.di.screen.ShowToursSubcomponent
import com.example.feature_tours.domain.interactor.IToursInteractor
import com.example.feature_tours.domain.model.Tour
import com.example.feature_tours.presentation.model.Response
import javax.inject.Inject

class ShowToursViewModel @Inject constructor(private val interactor: IToursInteractor) : BaseViewModel() {

    private val responseLiveData = MutableLiveData<Response<List<Tour>>>()

    init {
        loadTours()
    }

    fun getTours(): LiveData<Response<List<Tour>>> {
        return responseLiveData
    }

    fun loadTours(fromRefresh: Boolean = false) {
        interactor.loadTours()
            .compose(RxSchedulersUtil.getIOToMainSingle())
            .doOnSubscribe {
                if (!fromRefresh) responseLiveData.value = Response.Loading
            }
            .subscribe(
                {
                    responseLiveData.value = Response.Done(it)
                },
                {
                    responseLiveData.value = Response.Error(it)
                }
            )
            .untilCleared()
    }

    override fun onCleared() {
        ShowToursSubcomponent
            .release()
        super.onCleared()
    }
}