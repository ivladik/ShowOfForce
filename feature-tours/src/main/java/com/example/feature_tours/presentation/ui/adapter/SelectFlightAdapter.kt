package com.example.feature_tours.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.presentation.ui.adapter.OnInternalClickListener
import com.example.feature_tours.R
import com.example.feature_tours.databinding.LiEmptyDataBinding
import com.example.feature_tours.databinding.LiSelectFlightBinding
import com.example.feature_tours.databinding.LiSelectFlightFooterBinding
import com.example.feature_tours.domain.model.AvailableEntireTourDomainModel
import com.example.feature_tours.presentation.model.Response
import kotlinx.android.synthetic.main.li_select_flight_footer.view.*

private const val FOOTER_VIEW_TYPE = 1
private const val EMPTY_DATA_VIEW_TYPE = 2

class SelectFlightAdapter(private val onEntireTourClickListener: OnEntireTourAppliedListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val availableEntireTours = mutableListOf<AvailableEntireTourDomainModel>()

    private var lastSelectedPosition = -1

    fun update(updatedEntireTours: List<AvailableEntireTourDomainModel>?) {
        val sortedTours =
            updatedEntireTours?.sortedBy { it.price } ?: listOf()
        availableEntireTours.apply {
            clear()
            addAll(sortedTours)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            FOOTER_VIEW_TYPE -> {
                val binding: LiSelectFlightFooterBinding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.li_select_flight_footer,
                    parent,
                    false
                )
                FooterViewHolder(binding)
            }
            EMPTY_DATA_VIEW_TYPE -> {
                val binding: LiEmptyDataBinding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.li_empty_data,
                    parent,
                    false
                )
                EmptyDataViewHolder(binding)
            }
            else -> {
                val binding: LiSelectFlightBinding =
                    DataBindingUtil.inflate(inflater, R.layout.li_select_flight, parent, false)
                SelectFlightHolder(binding)
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
            is SelectFlightHolder -> {
                holder.bind(position)
            }
            is FooterViewHolder -> holder.bind()
            is EmptyDataViewHolder -> holder.bind()
        }
    }

    private inner class SelectFlightHolder(val binding: LiSelectFlightBinding) :
        RecyclerView.ViewHolder(binding.root), OnEntireTourSelectedListener {

        override fun onEntireTourSelected() {
            lastSelectedPosition = adapterPosition
            notifyDataSetChanged()
        }

        fun bind(position: Int) {
            binding.availableEntireTour = availableEntireTours[position]
            binding.onEntireTourSelectedListener = this
            binding.isChecked = lastSelectedPosition == position
        }
    }

    private inner class FooterViewHolder(val binding: LiSelectFlightFooterBinding) :
        RecyclerView.ViewHolder(binding.root), OnInternalClickListener {

        fun bind() {
            binding.onInternalClickListener = this
        }

        override fun onInternalClick() {
            val selectedTour =
                if (lastSelectedPosition == -1) null else availableEntireTours[lastSelectedPosition]
            val response = if (availableEntireTours.isEmpty()) {
                Response.Error()
            } else {
                Response.Done(selectedTour)
            }
            onEntireTourClickListener.onEntireTourApplied(response)
        }
    }

    private inner class EmptyDataViewHolder(val binding: LiEmptyDataBinding) :
        RecyclerView.ViewHolder(binding.root), OnInternalClickListener {

        fun bind() {
                itemView.applyButton.text = itemView.context.getString(R.string.ok_text) // TODO: create variable
                binding.onInternalClickListener = this
        }

        override fun onInternalClick() {
            val response = Response.Error()
            onEntireTourClickListener.onEntireTourApplied(response)
        }
    }

    companion object {

        @BindingAdapter("app:entire_tour_price")
        @JvmStatic
        fun setPossibleFlights(textView: TextView, price: Int) {
            with(textView) {
                text = context
                    .getString(
                        R.string.select_flight_adapter_price_text,
                        price.toString()
                    )
            }
        }
    }
}