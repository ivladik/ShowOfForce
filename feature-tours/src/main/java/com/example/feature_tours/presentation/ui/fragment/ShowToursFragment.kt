package com.example.feature_tours.presentation.ui.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.extension.gone
import com.example.core.extension.visible
import com.example.core.presentation.ui.custom_view.LoadingView
import com.example.feature_tours.R
import com.example.feature_tours.di.ToursSubcomponent
import com.example.feature_tours.di.screen.show_tours.ShowToursSubcomponent
import com.example.feature_tours.domain.model.Tour
import com.example.feature_tours.extension.injectViewModel
import com.example.feature_tours.presentation.model.Response
import com.example.feature_tours.presentation.ui.adapter.ShowToursAdapter
import com.example.feature_tours.presentation.ui.dialog.SelectFlightDialogFragment
import com.example.feature_tours.presentation.view_model.ShowToursViewModel
import kotlinx.android.synthetic.main.fr_show_tours.*
import timber.log.Timber
import java.lang.IllegalStateException
import javax.inject.Inject

class ShowToursFragment : Fragment(), ShowToursAdapter.OnTourClickListener {

    companion object {

        const val TAG = "ShowToursFragment"

        private const val SHOW_TOURS_REQUEST_CODE = 1

        fun newInstance(): ShowToursFragment {
            return ShowToursFragment()
        }
    }

    private val adapter by lazy {
        ShowToursAdapter(
            this
        )
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ShowToursViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ShowToursSubcomponent.create(
            ToursSubcomponent.instance
                ?.showToursSubcomponent()
        )
            ?.injectShowToursFragment(this)
            ?: throw IllegalStateException("ShowToursSubcomponent cannot be null")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fr_show_tours, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        hideRefresh()
        viewModel = injectViewModel(viewModelFactory)
        viewModel.loadTours()
        viewModel.responseLiveData.observe(
            this, Observer {
                processResponse(it)
            }
        )
    }

    private fun processResponse(response: Response<List<Tour>>) {
        when (response) {
            is Response.Loading -> {
                showProgress()
            }
            is Response.Done -> {
                hideProgress()
                showTours(response.data)
            }
            is Response.Error -> {
                hideProgress()
                showError()
            }
        }
    }

    override fun onDetach() {
        ShowToursSubcomponent
            .release()
        super.onDetach()
    }

    private fun showRefresh() {
        swipeRefreshLayout.apply {
            isRefreshing = false
            setOnRefreshListener {
                viewModel.loadToursFromRefresh()
            }
            isEnabled = true
        }
    }

    private fun hideRefresh() {
        swipeRefreshLayout.apply {
            isRefreshing = false
            setOnRefreshListener(null)
            isEnabled = false
        }
    }

    private fun setRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun showTours(tours: List<Tour>?) {
        showRefresh()
        if (tours.isNullOrEmpty()) {
            emptyDataStub.visible()
            recyclerView.gone()
        } else {
            emptyDataStub.gone()
            recyclerView.visible()
            adapter.updateTours(tours)
        }
    }

    private fun showError() {
        hideRefresh()
        recyclerView.gone()
        loadingView.setState(LoadingView.State.ERROR)
        loadingView.setRefreshButtonClickAction {
            viewModel.loadTours()
        }
    }

    private fun showProgress() {
        recyclerView.gone()
        loadingView.setState(LoadingView.State.LOADING)
    }

    private fun hideProgress() {
        loadingView.setState(LoadingView.State.DONE)
    }

    override fun onTourClick(tour: Tour) {
        val fragment = SelectFlightDialogFragment.newInstance(tour.id)
        fragment.setTargetFragment(
            this,
            SHOW_TOURS_REQUEST_CODE
        )
        fragment.show(requireFragmentManager(), SelectFlightDialogFragment.TAG)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                SHOW_TOURS_REQUEST_CODE -> {
                    val selectedAvailableEntireTourText =
                        data?.getStringExtra(SelectFlightDialogFragment.TAG_SELECTED_AVAILABLE_ENTIRE_TOUR_TEXT)
                    Toast.makeText(
                        requireContext(),
                        "Вы выбрали: $selectedAvailableEntireTourText",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        } else {
            Timber.i("Response Code isn't OK")
        }
    }
}