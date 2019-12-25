package com.example.feature_tours.presentation.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.extension.inflate
import com.example.feature_tours.R
import com.example.feature_tours.domain.model.AvailableEntireTourDomainModel
import com.example.feature_tours.presentation.model.Response
import kotlinx.android.synthetic.main.li_select_flight.view.*
import kotlinx.android.synthetic.main.li_select_flight_footer.view.*

private const val FOOTER_VIEW_TYPE = 1
private const val EMPTY_DATA_VIEW_TYPE = 2

class SelectFlightAdapter(onEntireTourClickListener: OnEntireTourAppliedListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val internalOnEntireTourClickListener = View.OnClickListener {
        val selectedTour =
            if (lastSelectedPosition == -1) null else availableEntireTours[lastSelectedPosition]
        val response = if (availableEntireTours.isEmpty()) {
            Response.createErrorInstance()
        } else {
            Response.createDoneInstance(selectedTour)
        }
        onEntireTourClickListener.onEntireTourApplied(response)
    }

    private val availableEntireTours = mutableListOf<AvailableEntireTourDomainModel>()

    private var lastSelectedPosition = -1

    fun update(updatedEntireTours: List<AvailableEntireTourDomainModel>?) {
        availableEntireTours.apply {
            clear()
            addAll(updatedEntireTours ?: listOf()) // TODO: resolve by change response model
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FOOTER_VIEW_TYPE -> {
                FooterViewHolder(parent.inflate(R.layout.li_select_flight_footer))
            }
            EMPTY_DATA_VIEW_TYPE -> {
                EmptyDataViewHolder(parent.inflate(R.layout.li_empty_data))
            }
            else -> {
                SelectFlightHolder(parent.inflate(R.layout.li_select_flight))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            availableEntireTours.isEmpty() -> {
                EMPTY_DATA_VIEW_TYPE
            }
            position == availableEntireTours.size -> {
                FOOTER_VIEW_TYPE
            }
            else -> {
                super.getItemViewType(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return availableEntireTours.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SelectFlightHolder -> holder.bind(availableEntireTours[position], position)
            is FooterViewHolder -> holder.bind()
            is EmptyDataViewHolder -> holder.bind()
        }
    }

    interface OnEntireTourAppliedListener {

        fun onEntireTourApplied(result: Response<AvailableEntireTourDomainModel?>)
    }

    private inner class SelectFlightHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(availableEntireTour: AvailableEntireTourDomainModel, position: Int) {
            with(itemView) {
                airlineButton.text = availableEntireTour.airlineName
                airlineButton.isChecked = lastSelectedPosition == position
                selectContainer.setOnClickListener {
                    airlineButton.isChecked = true
                    lastSelectedPosition = adapterPosition
                    notifyDataSetChanged()
                }
                entireTourPrice.text = context.getString(
                    R.string.select_flight_adapter_price_text,
                    availableEntireTour.price.toString()
                )
            }
        }
    }

    private inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind() {
            with(itemView) {
                applyButton.setOnClickListener(internalOnEntireTourClickListener)
            }
        }
    }

    private inner class EmptyDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind() {
            with(itemView) {
                applyButton.text = context.getString(R.string.ok_text)
                applyButton.setOnClickListener(internalOnEntireTourClickListener)
            }
        }
    }
}