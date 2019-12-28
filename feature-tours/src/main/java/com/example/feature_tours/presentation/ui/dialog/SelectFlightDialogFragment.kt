package com.example.feature_tours.presentation.ui.dialog

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.extension.visible
import com.example.feature_tours.R
import com.example.feature_tours.di.ToursSubcomponent
import com.example.feature_tours.di.screen.select_flights.SelectFlightSubcomponent
import com.example.feature_tours.domain.model.AvailableEntireTourDomainModel
import com.example.feature_tours.extension.injectViewModel
import com.example.feature_tours.presentation.model.Response
import com.example.feature_tours.presentation.ui.adapter.SelectFlightAdapter
import com.example.feature_tours.presentation.view_model.SelectFlightViewModel
import kotlinx.android.synthetic.main.dlg_select_flight.*
import java.lang.IllegalStateException
import javax.inject.Inject

class SelectFlightDialogFragment : DialogFragment(),
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
        SelectFlightAdapter(this)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SelectFlightViewModel

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
        return inflater.inflate(
            R.layout.dlg_select_flight,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        viewModel = injectViewModel(viewModelFactory)
        loadAvailableEntireTours()
    }

    private fun loadAvailableEntireTours() {
        val tourId =
            arguments?.getInt(
                AVAILABLE_ENTIRE_TOURS_KEY,
                DEFAULT_VALUE
            ) // TODO: implement error handling
                ?: throw IllegalStateException("Arguments cannot be null")
        viewModel.loadAvailableEntireTours(tourId)
        viewModel.responseLiveData.observe(
            this, Observer {
                processResponse(it)
            }
        )
    }

    private fun processResponse(response: Response<List<AvailableEntireTourDomainModel>>) {
        when (response) {
            is Response.Done -> {
                showAvailableEntireTours(response.data)
            }
        }
    }

    private fun setRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun showAvailableEntireTours(availableEntireTours: List<AvailableEntireTourDomainModel>?) {
        recyclerView.visible()
        adapter.update(availableEntireTours)
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
