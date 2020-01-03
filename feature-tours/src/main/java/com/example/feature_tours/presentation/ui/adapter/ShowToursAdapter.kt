package com.example.feature_tours.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_tours.R
import com.example.feature_tours.databinding.LiTourBinding
import com.example.feature_tours.domain.model.Tour
import com.example.feature_tours.util.ToursDiffUtilCallback

class ShowToursAdapter(private val onClickListener: OnTourClickListener) :
    RecyclerView.Adapter<ShowToursAdapter.ToursHolder>(), OnTourClickListener {

    private val tours = mutableListOf<Tour>()

    override fun onTourClick(tour: Tour) {
        onClickListener.onTourClick(tour)
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
        val inflater = LayoutInflater.from(parent.context)
        val binding: LiTourBinding = DataBindingUtil.inflate(inflater, R.layout.li_tour, parent, false)
        return ToursHolder(binding)
    }

    override fun getItemCount(): Int = tours.size

    override fun onBindViewHolder(holder: ToursHolder, position: Int) {
        val tour = tours[position]
        holder.binding.tour = tour
        holder.binding.onTourClickListener = this

    }

    inner class ToursHolder(val binding: LiTourBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {

        @BindingAdapter("app:possible_flights")
        @JvmStatic
        fun setPossibleFlights(textView: TextView, quantity: Int) {
            textView.text = textView.context
                .resources
                .getQuantityString(
                    R.plurals.possibleFlights,
                    quantity,
                    quantity
                )
        }

        @BindingAdapter("app:minimal_price")
        @JvmStatic
        fun setMinimalPrice(textView: TextView, quantity: Int) {
            textView.text = textView.context
                .resources
                .getQuantityString(
                    R.plurals.minimalPrice,
                    quantity,
                    quantity
                )
        }
    }
}