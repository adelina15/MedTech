package com.example.medtech.view.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medtech.R
import com.example.medtech.data.model.Time
import com.example.medtech.databinding.HourBinding
import com.example.medtech.utils.Delegates

class HoursAdapter(val hourClicked: Delegates.HourClicked) : RecyclerView.Adapter<HoursAdapter.HourViewHolder>() {
    private var selectedItemPosition: Int = -1
    private var list = listOf<Time>()
    fun setList(list: List<Time>) {
        this.list = list
        notifyDataSetChanged()
    }

    class HourViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = HourBinding.bind(item)
        fun bind(hours: Time) = with(binding) {
            hour.text = "${hours.start} - ${hours.end}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.hour, parent, false)
        return HourViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HourViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.bind(list[position])
        holder.binding.hour.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
            hourClicked.onItemClick(list[position])
        }
        if (selectedItemPosition == position) {
            holder.binding.hour.setBackgroundResource(R.drawable.blue_box)
            holder.binding.hour.setTextColor(Color.parseColor("#39B7CD"))
        } else {
            holder.binding.hour.setBackgroundResource(R.drawable.rounded_box)
            holder.binding.hour.setTextColor(Color.parseColor("#2D2D2D"))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}