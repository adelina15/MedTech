package com.example.medtech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.medtech.adapter.WeeksAdapter
import com.example.medtech.data.Week
import com.example.medtech.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), Delegates.WeekClicked {
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!
    private val weekAdapter by lazy { WeeksAdapter(this) }

    val itemList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for (i in 1..20){
            itemList.add("$i")
        }
        with(binding.toolbar) {
            inflateMenu(R.menu.main_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.lamp -> {
                        Toast.makeText(requireContext(), "полезная информация", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.bell -> {
                        Toast.makeText(requireContext(), "оповещения", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
        }
        binding.exclamation.setOnClickListener{
            Toast.makeText(requireContext(), "важно ходить к врачу своевременно", Toast.LENGTH_SHORT).show()
        }
        binding.weeksRv.adapter = weekAdapter
        weekAdapter.setList(itemList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(week: Week) {

    }
}