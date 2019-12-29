package com.example.feature_tours.presentation.ui.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.extension.gone
import com.example.core.extension.visible
import com.example.core.presentation.ui.custom_view.LoadingView
import com.example.feature_tours.R
import com.example.feature_tours.di.ToursSubcomponent
import com.example.feature_tours.di.screen.show_tours.ShowToursSubcomponent
import com.example.feature_tours.domain.model.Tour
import com.example.feature_tours.presentation.presenter.ShowToursPresenter
import com.example.feature_tours.presentation.ui.adapter.ShowToursAdapter
import com.example.feature_tours.presentation.ui.dialog.SelectFlightDialogFragment
import com.example.feature_tours.presentation.ui.view.ShowToursView
import kotlinx.android.synthetic.main.fr_show_tours.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import timber.log.Timber
import java.lang.IllegalStateException
import javax.inject.Inject

class ShowToursFragment : MvpAppCompatFragment(), ShowToursAdapter.OnTourClickListener,
    ShowToursView {

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

    // TODO: lsv

    @Inject
    @InjectPresenter
    lateinit var presenter: ShowToursPresenter

    @ProvidePresenter
    fun providePresenter(): ShowToursPresenter {
        ShowToursSubcomponent.create(
            ToursSubcomponent.instance
                ?.showToursSubcomponent()
        )
            ?.injectShowToursFragment(this)
            ?: throw IllegalStateException("ShowToursSubcomponent cannot be null")
        return presenter
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
    }

    private fun showRefresh() {
        swipeRefreshLayout.apply {
            isRefreshing = false
            setOnRefreshListener {
                presenter.loadToursFromRefresh()
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

    override fun showTours(tours: List<Tour>) {
        showRefresh()
        if (tours.isEmpty()) {
            emptyDataStub.visible()
            recyclerView.gone()
        } else {
            emptyDataStub.gone()
            recyclerView.visible()
            adapter.updateTours(tours)
        }
    }

    override fun showError() {
        hideRefresh()
        recyclerView.gone()
        loadingView.setState(LoadingView.State.ERROR)
        loadingView.setRefreshButtonClickAction {
            presenter.loadTours()
        }
    }

    override fun showProgress() {
        recyclerView.gone()
        loadingView.setState(LoadingView.State.LOADING)
    }

    override fun hideProgress() {
        loadingView.setState(LoadingView.State.DONE)
    }

    override fun onTourClick(tour: Tour) {
        val fragment = SelectFlightDialogFragment.newInstance(tour.id)
        fragment.setTargetFragment(this,
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
            Timber.i("Result Code isn't OK")
        }
    }
}