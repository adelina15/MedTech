package com.example.medtech.view.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.medtech.R
import com.example.medtech.view.adapter.HoursAdapter
import com.example.medtech.data.model.Time
import com.example.medtech.databinding.FragmentScheduleBinding
import com.example.medtech.utils.Delegates
import com.example.medtech.viewmodel.ScheduleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ScheduleFragment : Fragment(), Delegates.HourClicked {

    private var _binding: FragmentScheduleBinding? = null
    private val binding
        get() = _binding!!

    private val hourAdapter by lazy { HoursAdapter(this) }
    private val scheduleViewModel by viewModel<ScheduleViewModel>()

    lateinit var curDate: String

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
        setupObservers()
        binding.hoursRv.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.hoursRv.adapter = hourAdapter
        Log.i("schedule", "${binding.calendarView.date} 1")
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
//            scheduleViewModel.getFreeTime()
            binding.dateCalendar.text = "$dayOfMonth ${getMonthName(month)}"
            Log.i("schedule", "$dayOfMonth.${month+1}.$year 2")
        }
    }

    private fun setupObservers() {
        scheduleViewModel.freeTime.observe(requireActivity()) {
            hourAdapter.setList(it.free_times)
        }
        scheduleViewModel.errorMessage.observe(requireActivity()) {
            Log.i("schedule", it)
            Toast.makeText(requireContext(), "Что-то пошло не так", Toast.LENGTH_SHORT).show()
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

    override fun onItemClick(hour: Time) {
        with(binding.chooseButton){
            setTextColor(Color.parseColor("#1BB3C8"))
            setBackgroundColor(Color.parseColor("#FFFFFFFF"))
            setOnClickListener {
                alertDialog()
            }
        }
    }

    private fun alertDialog() {
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
            form()
            builder.dismiss()
        }
        builder.show()

        //To make AlertDialog smaller in width
        val width = (resources.displayMetrics.widthPixels * 0.70).toInt()
        // Get the current app screen width and height
        val window: Window? = builder.window
        window!!.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    private fun form() {
        //custom AlertDialog
        val formBuilder = AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom).create()
        val view = layoutInflater.inflate(R.layout.form, null)
        val close = view.findViewById<ImageView>(R.id.closeButton)
        val save = view.findViewById<Button>(R.id.saveButton)
        val spinner = view.findViewById<Spinner>(R.id.spinner)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.type_array,
            R.layout.spinner_list
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_list)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        formBuilder.setView(view)
        close.setOnClickListener {
            formBuilder.dismiss()
        }
        save.setOnClickListener {
            Toast.makeText(requireContext(), "Вы успешно записались", Toast.LENGTH_SHORT).show()
            binding.appointmentInfo.visibility = View.VISIBLE
            binding.chooseButton.text = "Вы записаны"
            formBuilder.dismiss()
        }
        formBuilder.show()
    }

}