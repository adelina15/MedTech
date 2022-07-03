package com.example.medtech.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
        binding.hoursRv.adapter = hourAdapter
        hourAdapter.setList(hoursList)
//        val date = binding.calendarView.date
//        binding.calendarView.setOnDateChangeListener { calendarView, i, i2, i3 ->
//            curDate = "$i3 ${i2.toString()}"
//            binding.dateCalendar.text = curDate
//        }


//        with(binding.toolbar) {
//            inflateMenu(R.menu.main_menu)
//            setOnMenuItemClickListener {
//                when (it.itemId) {
//                    R.id.lamp -> {
//                        Toast.makeText(requireContext(), "полезная информация", Toast.LENGTH_SHORT)
//                            .show()
//                        true
//                    }
//                    R.id.bell -> {
//                        Toast.makeText(requireContext(), "оповещения", Toast.LENGTH_SHORT).show()
//                        true
//                    }
//                    else -> false
//                }
//            }
//        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}