package com.example.medtech.view.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.medtech.R
import com.example.medtech.data.UserPreferences
import com.example.medtech.databinding.FormBinding
import com.example.medtech.databinding.FragmentScheduleBinding
import com.example.medtech.utils.Delegates
import com.example.medtech.view.adapter.HoursAdapter
import com.example.medtech.viewmodel.ScheduleViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class ScheduleFragment : Fragment(), Delegates.HourClicked {

    private lateinit var binding: FragmentScheduleBinding

    private val sharedPreferences by inject<UserPreferences>()
    private val hourAdapter by lazy { HoursAdapter(this) }
    private val scheduleViewModel by viewModel<ScheduleViewModel>()
    private var doctorId = 1
    private var patientId = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false)
        doctorId = sharedPreferences.fetchDoctorId()
        patientId = sharedPreferences.fetchUserId()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Calendar.getInstance().time
        val dateToday = formatter.format(date)
        scheduleViewModel.setDate(dateToday)
        scheduleViewModel.getFreeTime(doctorId, dateToday)
        Log.i("schedule", "${scheduleViewModel.getFreeTime(doctorId, dateToday)} getfreetime")
        setupObservers()
        Log.i("schedule", "$dateToday date today")

        binding.hoursRv.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.hoursRv.adapter = hourAdapter
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            binding.dateCalendar.text = "$dayOfMonth ${getMonthName(month)}"
            //месяцы до 10 в setOnDateChangeListener выдаются однозачными, меняем в двузначные
            var monthWithTwoNumbers = "${month + 1}"
            if (monthWithTwoNumbers.length == 1) {
                monthWithTwoNumbers = "0$monthWithTwoNumbers"
            }
            val selectedDate = "$year-${monthWithTwoNumbers}-$dayOfMonth"
            scheduleViewModel.getFreeTime(doctorId, selectedDate)
            scheduleViewModel.setDate(selectedDate)
            Log.i("schedule", "$selectedDate selected date")
        }
    }

    private fun setupObservers() {
        scheduleViewModel.freeTime.observe(requireActivity()) {
            if (it.free_times.isNotEmpty()){
                hourAdapter.setList(it.free_times)
            }
            else showNoAvailableTime()
        }
        scheduleViewModel.errorMessage.observe(requireActivity()) {
            Log.i("schedule", it.response)
            binding.noAvailableTime.text = it.response
            showNoAvailableTime()
        }
    }

    private fun getMonthName(monthNum: Int): String {
        return when (monthNum) {
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

    override fun onItemClick(hourId: Int) {
        with(binding.chooseButton) {
            setTextColor(Color.parseColor("#1BB3C8"))
            setBackgroundColor(Color.parseColor("#FFFFFFFF"))
            setOnClickListener {
                alertDialog(hourId)
            }
        }
    }

    private fun alertDialog(hourId: Int) {
        //custom AlertDialog
        val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom).create()
        val view = layoutInflater.inflate(R.layout.confirm_layout, null)
        val no = view.findViewById<TextView>(R.id.noButton)
        val yes = view.findViewById<TextView>(R.id.yesButton)
        builder.setView(view)
        no.setOnClickListener {
            builder.dismiss()
        }
        yes.setOnClickListener {
            form(hourId)
            builder.dismiss()
        }
        builder.show()

        //To make AlertDialog smaller in width
        val width = (resources.displayMetrics.widthPixels * 0.70).toInt()
        // Get the current app screen width and height
        val window: Window? = builder.window
        window!!.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    private fun form(hourId: Int) {
        //custom AlertDialog

        val formBinding: FormBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.form, null, false
        )
        val formBuilder = AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom).create()
        val close = formBinding.closeButton
        val save = formBinding.saveButton
        formBuilder.setView(formBinding.root)
        formBinding.doctorName.text = sharedPreferences.fetchDoctorName()
        close.setOnClickListener {
            formBuilder.dismiss()
        }
        save.setOnClickListener {
            scheduleViewModel.makeAppointment(formBinding.additionalMessage.text.toString(), formBinding.editTextReason.text.toString(), hourId, doctorId, patientId)
            showSuccess()
            binding.appointmentInfo.visibility = View.VISIBLE
            binding.chooseButton.text = "Вы записаны"
            formBuilder.dismiss()
        }
        formBuilder.show()
    }

    private fun showSuccess() {
        //custom AlertDialog
        val successBuilder = AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom).create()
        val view = layoutInflater.inflate(R.layout.success_record, null)
        successBuilder.setView(view)
        successBuilder.show()

        //To make AlertDialog smaller in width
        val width = (resources.displayMetrics.widthPixels * 0.70).toInt()
        // Get the current app screen width and height
        val window: Window? = successBuilder.window
        window!!.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    private fun showNoAvailableTime(){
        binding.noAvailableTime.visibility = View.VISIBLE
        binding.noChoiceButton.visibility = View.VISIBLE
        binding.chooseButton.visibility = View.GONE
    }
}