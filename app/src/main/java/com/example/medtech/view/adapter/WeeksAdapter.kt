package com.example.medtech.view.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medtech.utils.Delegates
import com.example.medtech.R
import com.example.medtech.databinding.WeekBinding

class WeeksAdapter(val weekClicked: Delegates.WeekClicked) :
    RecyclerView.Adapter<WeeksAdapter.WeekViewHolder>() {

    private var selectedItemPosition: Int = -1
    private var list = ArrayList<String>()
    fun setList(list: ArrayList<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    class WeekViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = WeekBinding.bind(item)
        fun bind(week: String) = with(binding) {
            weekNumber.text = week
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.week, parent, false)
        return WeekViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeekViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(list[position])
        holder.binding.cardView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }
        if (selectedItemPosition == position) {
            holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#39B7CD"))
            holder.binding.weekNumber.setTextColor(Color.parseColor("#FFFFFFFF"))
        }
        else {
            holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
            holder.binding.weekNumber.setTextColor(Color.parseColor("#2D2D2D"))
            holder.binding.cardView.strokeColor = R.color.black
            holder.binding.cardView.strokeWidth = 1
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}