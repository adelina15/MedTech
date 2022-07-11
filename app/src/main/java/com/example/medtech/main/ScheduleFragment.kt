package com.example.medtech.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.medtech.R
import com.example.medtech.adapter.HoursAdapter
import com.example.medtech.data.Hour
import com.example.medtech.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment() {

    private var _binding: FragmentScheduleBinding? = null
    private val binding
        get() = _binding!!

    private val hourAdapter by lazy { HoursAdapter() }
    lateinit var curDate: String

    private val hoursList by lazy {
        mutableListOf(
            Hour("09:00 - 09:30"),
            Hour("10:00 - 10:30"),
            Hour("10:30 - 11:00"),
            Hour("14:00 - 14:30"),
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.hoursRv.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.hoursRv.adapter = hourAdapter
        hourAdapter.setList(hoursList)
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            binding.dateCalendar.text = "$dayOfMonth ${getMonthName(month)}"
        }
    }

    private fun getMonthName(monthNum: Int): String {
        return when(monthNum){
            0 -> "января"
            1 -> "февраля"
            2 -> "марта"
            3 -> "апреля"
            4 -> "мая"
            5 -> "июня"
            6 -> "июля"
            7 -> "августа"
            8 -> "сентября"
            9 -> "октября"
            10 -> "ноября"
            11 -> "декабря"
            else -> ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}