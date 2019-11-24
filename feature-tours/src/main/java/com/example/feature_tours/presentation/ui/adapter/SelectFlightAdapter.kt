package com.example.feature_tours.presentation.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.extension.inflate
import com.example.feature_tours.R
import com.example.feature_tours.domain.model.AvailableEntireTourDomainModel
import kotlinx.android.synthetic.main.li_select_flight.view.*
import kotlinx.android.synthetic.main.li_select_flight_footer.view.*

private const val FOOTER_VIEW_TYPE = 1

class SelectFlightAdapter(onClickListener: OnEntireTourAppliedListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // TODO: Base RecyclerView

    private val internalClickListener = View.OnClickListener {
        val selectedTour =
            if (lastSelectedPosition == -1) null else availableEntireTours[lastSelectedPosition]
        onClickListener.onEntireTourApplied(selectedTour)
    }

    private val availableEntireTours = mutableListOf<AvailableEntireTourDomainModel>()

    private var lastSelectedPosition = -1

    fun update(updatedEntireTours: List<AvailableEntireTourDomainModel>) {
        availableEntireTours.apply {
            clear()
            addAll(updatedEntireTours)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FOOTER_VIEW_TYPE -> {
                FooterViewHolder(parent.inflate(R.layout.li_select_flight_footer))
            }
            else -> {
                SelectFlightHolder(parent.inflate(R.layout.li_select_flight))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == availableEntireTours.size) {
            FOOTER_VIEW_TYPE
        } else {
            super.getItemViewType(position)
        }
    }

    override fun getItemCount(): Int {
        return availableEntireTours.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SelectFlightHolder -> holder.bind(availableEntireTours[position], position)
            is FooterViewHolder -> holder.bind()
        }
    }

    interface OnEntireTourAppliedListener {

        fun onEntireTourApplied(availableEntireTour: AvailableEntireTourDomainModel?)
    }

    inner class SelectFlightHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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

    inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind() {
            with(itemView) {
                applyButton.setOnClickListener(internalClickListener)
            }
        }
    }
}