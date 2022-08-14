package com.example.medtech.view.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medtech.utils.Delegates
import com.example.medtech.R
import com.example.medtech.data.model.Week
import com.example.medtech.databinding.WeekBinding

class WeeksAdapter(val weekClicked: Delegates.WeekClicked) :
    RecyclerView.Adapter<WeeksAdapter.WeekViewHolder>() {

    private var week: Int = 1
    private var selectedItemPosition: Int = week - 1
    fun setCurrentWeek(week: Int) {
        this.week = week
        notifyDataSetChanged()
    }

    private var list = listOf<Week>()
    fun setList(list: MutableList<Week>) {
        this.list = list
        notifyDataSetChanged()
    }

    class WeekViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = WeekBinding.bind(item)
        fun bind(week: Week) = with(binding) {
            weekNumber.text = week.week.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.week, parent, false)
        return WeekViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: WeekViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.bind(list[position])
        holder.binding.cardView.setOnClickListener {
            weekClicked.onItemClick(list[position])
            selectedItemPosition = position
            notifyDataSetChanged()
        }
        //when week is clicked, change color of this box
        if (selectedItemPosition == position) {
            holder.binding.cardView.strokeColor = Color.parseColor("#39B7CD")
            holder.binding.cardView.strokeWidth = 2
            holder.binding.weekNumber.setTextColor(Color.parseColor("#39B7CD"))
        }
        //other (not clicked) boxes stay white
        else {
            holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
            holder.binding.weekNumber.setTextColor(Color.parseColor("#2D2D2D"))
            holder.binding.cardView.strokeColor = R.color.black
            holder.binding.cardView.strokeWidth = 1
        }
        //current week box should always be of different
        if (list[position].week == week){
            holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#39B7CD"))
            holder.binding.weekNumber.setTextColor(Color.parseColor("#FFFFFFFF"))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}