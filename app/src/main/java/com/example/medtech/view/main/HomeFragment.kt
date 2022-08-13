package com.example.medtech.view.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.medtech.utils.Delegates
import com.example.medtech.R
import com.example.medtech.data.UserPreferences
import com.example.medtech.data.model.BabyItem
import com.example.medtech.view.adapter.WeeksAdapter
import com.example.medtech.data.model.Week
import com.example.medtech.databinding.FragmentHomeBinding
import com.example.medtech.viewmodel.AuthViewModel
import com.example.medtech.viewmodel.BabyViewModel
import com.example.medtech.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), Delegates.WeekClicked {
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!
    private val weekAdapter by lazy { WeeksAdapter(this) }
    private lateinit var sharedPreferences: UserPreferences
    private val babyViewModel by viewModel<BabyViewModel>()
    private val userViewModel by viewModel<UserViewModel>()
    private val itemList = mutableListOf<Week>()
    private var babyItem: BabyItem? = null
    private var week = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        sharedPreferences = UserPreferences(requireContext())
        userViewModel.getProfileById(sharedPreferences.fetchUserId())
        setupObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgressBar()
        babyViewModel.getBabyById(1)
        babyViewModel.baby.observe(requireActivity()) {
            hideProgressBar()
            babyItem = it
            if (isAdded) {
                with(binding) {
                    Glide.with(requireContext()).load(babyItem?.fruit_img).into(fruit)
                    Glide.with(requireContext()).load(babyItem?.baby_img).into(baby)
                    description.text = babyItem?.title
                    dateCalendar.text = babyItem?.dates_of_advices
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
                        Toast.makeText(requireContext(), "оповещений нет", Toast.LENGTH_SHORT).show()
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
                showImportance()
            }
            weeksRv.adapter = weekAdapter
            foodButton.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToFoodFragment(babyItem!!.food)
                findNavController().navigate(action)
            }
            weightButton.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToWeightFragment(babyItem!!.mom_weight)
                findNavController().navigate(action)
            }
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

    private fun setupObservers() {
        userViewModel.user.observe(requireActivity()) {
            week = it.week_of_pregnancy
            sharedPreferences.saveDoctorId(it.doctor_field.id)
            sharedPreferences.saveDoctorName("${it.doctor_field.last_name} ${it.doctor_field.first_name}")
        }
        userViewModel.errorMessage.observe(requireActivity()) {
            Log.i("profile", it)
            Toast.makeText(requireContext(), "Что-то пошло не так", Toast.LENGTH_SHORT).show()
        }
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

    private fun showImportance() {
        //custom AlertDialog
        val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom).create()
        val view = layoutInflater.inflate(R.layout.important_info, null)
        builder.setView(view)
        builder.show()
    }
}