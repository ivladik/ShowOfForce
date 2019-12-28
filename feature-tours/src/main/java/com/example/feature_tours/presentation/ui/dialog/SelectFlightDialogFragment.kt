package com.example.feature_tours.presentation.ui.dialog

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.model.Response
import com.example.feature_tours.R
import com.example.feature_tours.di.ToursSubcomponent
import com.example.feature_tours.di.screen.select_flights.SelectFlightSubcomponent
import com.example.feature_tours.domain.model.AvailableEntireTourDomainModel
import com.example.feature_tours.presentation.presenter.SelectFlightDialogPresenter
import com.example.feature_tours.presentation.ui.adapter.SelectFlightAdapter
import com.example.feature_tours.presentation.ui.view.SelectFlightDialogView
import kotlinx.android.synthetic.main.dlg_select_flight.*
import moxy.MvpAppCompatDialogFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.lang.IllegalStateException
import javax.inject.Inject

class SelectFlightDialogFragment : MvpAppCompatDialogFragment(),
    SelectFlightDialogView,
    SelectFlightAdapter.OnEntireTourAppliedListener {

    // TODO: flights price filter, custom view for li

    companion object {

        const val TAG = "SelectFlightDialogFragment"
        const val TAG_SELECTED_AVAILABLE_ENTIRE_TOUR_TEXT = "selected_available_entire_tour_text"
        private const val AVAILABLE_ENTIRE_TOURS_KEY = "available_entire_tour_key"
        private const val DEFAULT_VALUE = -1

        fun newInstance(tourId: Int): SelectFlightDialogFragment {
            return SelectFlightDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt(
                        AVAILABLE_ENTIRE_TOURS_KEY,
                        tourId
                    )
                }
            }
        }
    }

    private val adapter by lazy {
        SelectFlightAdapter(
            this
        )
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: SelectFlightDialogPresenter

    @ProvidePresenter
    fun providePresenter(): SelectFlightDialogPresenter {
        return presenter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SelectFlightSubcomponent.create(
            ToursSubcomponent.instance
                ?.selectFlightSubcomponent()
        )
            ?.injectSelectFlightDialogFragment(this)
            ?: throw IllegalStateException("SelectFlightSubcomponent cannot be null")
    }

    override fun onDetach() {
        SelectFlightSubcomponent
            .release()
        super.onDetach()
    }

    override fun onStart() {
        super.onStart()
        configWindow()
    }

    private fun configWindow() {
        val metrics = resources.displayMetrics
        val width = metrics.widthPixels
        dialog?.window?.setLayout((6 * width) / 7, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.dlg_select_flight, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        loadAvailableEntireTours()
    }

    private fun loadAvailableEntireTours() {
        val tourId =
            arguments?.getInt(AVAILABLE_ENTIRE_TOURS_KEY, DEFAULT_VALUE) // TODO: implement error handling
                ?: throw IllegalStateException("Arguments cannot be null")
        presenter.loadAvailableEntireTours(tourId)
    }

    private fun setRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    override fun showAvailableEntireTours(availableEntireTours: List<AvailableEntireTourDomainModel>) {
        adapter.update(/*availableEntireTours*/null)
    }

    override fun onEntireTourApplied(result: Response<AvailableEntireTourDomainModel?>) {
        when (result) {
            is Response.Done -> {
                result.data?.let {
                    sendResult(it)
                    dismiss()
                }
                    ?: showNoTourSelectedToast()
            }
            else -> {
                dismiss()
            }
        }
    }

    private fun sendResult(availableEntireTour: AvailableEntireTourDomainModel) {
        val selectedAvailableEntireTourText = "${availableEntireTour.airlineName}, ${getString(
            R.string.select_flight_adapter_price_text,
            availableEntireTour.price.toString()
        )}"
        targetFragment?.onActivityResult(
            targetRequestCode,
            Activity.RESULT_OK,
            Intent().putExtra(
                TAG_SELECTED_AVAILABLE_ENTIRE_TOUR_TEXT, selectedAvailableEntireTourText
            )
        )
    }

    private fun showNoTourSelectedToast() {
        Toast.makeText(
            requireContext(),
            getString(R.string.no_tour_selected_message),
            Toast.LENGTH_LONG
        )
            .show()
    }
}
