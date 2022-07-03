package com.example.medtech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.medtech.adapter.HoursAdapter
import com.example.medtech.adapter.WeeksAdapter
import com.example.medtech.data.Hour
import com.example.medtech.databinding.FragmentHomeBinding
import com.example.medtech.databinding.FragmentScheduleBinding
import com.example.medtech.databinding.HourBinding

class ScheduleFragment : Fragment() {

    private var _binding: FragmentScheduleBinding? = null
    private val binding
        get() = _binding!!

    private val hourAdapter by lazy { HoursAdapter() }

    val itemList = ArrayList<Hour>(
        Hour("09:00 - 09:30"),
        Hour("10:00 - 10:30"),
        Hour("10:30 - 11:00"),
        Hour("14:00 - 14:30"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.hoursRv.adapter = hourAdapter
        hourAdapter.setList(itemList)

        super.onViewCreated(view, savedInstanceState)

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