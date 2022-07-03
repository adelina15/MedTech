package com.example.medtech.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medtech.R
import com.example.medtech.data.Hour
import com.example.medtech.databinding.HourBinding

class HoursAdapter: RecyclerView.Adapter<HoursAdapter.HourViewHolder>()  {
    private var list = ArrayList<Hour>()
    fun setList(list: ArrayList<Hour>) {
        this.list = list
        notifyDataSetChanged()
    }

    class HourViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = HourBinding.bind(item)
        fun bind(hours: Hour) = with(binding) {
            hour.text = hours.hour
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.week, parent, false)
        return HourViewHolder(view)
    }

    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}