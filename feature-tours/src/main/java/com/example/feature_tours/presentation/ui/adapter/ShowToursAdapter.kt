package com.example.feature_tours.presentation.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.extension.inflate
import com.example.feature_tours.R
import com.example.feature_tours.domain.model.Tour
import com.example.feature_tours.util.ToursDiffUtilCallback
import kotlinx.android.synthetic.main.li_tour.view.*

class ShowToursAdapter(onClickListener: OnTourClickListener) :
    RecyclerView.Adapter<ShowToursAdapter.ToursHolder>() {

    private val tours = mutableListOf<Tour>()

    private val internalClickListener = View.OnClickListener {
        val position = it.tag as Int
        onClickListener.onTourClick(tours[position])
    }

    fun updateTours(updatedTours: List<Tour>) {
        DiffUtil.calculateDiff(
            ToursDiffUtilCallback(
                tours,
                updatedTours
            )
        )
            .dispatchUpdatesTo(this)
        tours.clear()
        tours.addAll(updatedTours)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToursHolder {
        return ToursHolder(parent.inflate(R.layout.li_tour))
    }

    override fun getItemCount(): Int = tours.size

    override fun onBindViewHolder(holder: ToursHolder, position: Int) {
        holder.bind(tours[position], position)
    }

    interface OnTourClickListener {

        fun onTourClick(tour: Tour)
    }

    inner class ToursHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tour: Tour, position: Int) {
            with(itemView) {
                hotelName.text = tour.hotelName
                possibleFlights.text = context.resources
                    .getQuantityString(
                        R.plurals.possibleFlights,
                        tour.availableFlightsForHotel,
                        tour.availableFlightsForHotel
                    )
                minimalPrice.text = context.resources
                    .getQuantityString(
                        R.plurals.minimalPrice,
                        tour.availableFlightsForHotel,
                        tour.minimalPriceForEntireTour
                    )
                tag = position
                container.setOnClickListener(internalClickListener)
            }
        }
    }
}