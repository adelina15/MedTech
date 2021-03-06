package com.example.medtech.view.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.medtech.utils.Delegates
import com.example.medtech.R
import com.example.medtech.data.model.BabyItem
import com.example.medtech.view.adapter.WeeksAdapter
import com.example.medtech.data.model.Week
import com.example.medtech.databinding.FragmentHomeBinding
import com.example.medtech.viewmodel.AuthViewModel
import com.example.medtech.viewmodel.BabyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), Delegates.WeekClicked {
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!
    private val weekAdapter by lazy { WeeksAdapter(this) }
    private val babyViewModel by viewModel<BabyViewModel>()
    val itemList = mutableListOf<Week>()
    private var babyItem: BabyItem? = null

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
        lifecycle.addObserver(babyViewModel)
        showProgressBar()
        babyViewModel.getBabyById(1)
        babyViewModel.baby.observe(requireActivity()) {
            hideProgressBar()
            Log.i("mainServ", it.toString())
            babyItem = it
            if (isAdded) {
                with(binding) {
                    Glide.with(requireContext()).load(babyItem?.fruit_img).into(fruit)
                    Glide.with(requireContext()).load(babyItem?.baby_img).into(baby)
                    description.text = babyItem?.title
                    dateCalendar.text = "${babyItem?.week} ????????????"
                    weight.text = babyItem?.weight
                    height.text = babyItem?.height
                    advice.text = babyItem?.advices
                }
            }
        }
        babyViewModel.errorMessage.observe(requireActivity()) {
            Log.i("mainServ", it)
        }
        //healthy pregnancy lasts up to 42 weeks
        for (i in 1..42) {
            itemList.add(Week(i))
        }
        with(binding.toolbar) {
            inflateMenu(R.menu.main_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.lamp -> {
                        val action = HomeFragmentDirections.actionHomeFragmentToInfoFragment()
                        findNavController().navigate(action)
                        true
                    }
                    R.id.bell -> {
                        Toast.makeText(requireContext(), "????????????????????", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
        }
        with(binding) {
            readMore.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToWeekDetailsFragment(babyItem!!)
                findNavController().navigate(action)
            }
            exclamation.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    "?????????? ???????????? ?? ?????????? ????????????????????????",
                    Toast.LENGTH_SHORT
                ).show()
            }
            weeksRv.adapter = weekAdapter
        }
        weekAdapter.setList(itemList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(week: Week) {
        getWeek(week)
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    //only for testing, remove after full connection
    private fun getWeek(week: Week) {
        when (week.week) {
            5 -> babyViewModel.getBabyById(1)
            7 -> babyViewModel.getBabyById(2)
            10 -> babyViewModel.getBabyById(5)
            else -> babyViewModel.getBabyById(5)
        }
    }
}