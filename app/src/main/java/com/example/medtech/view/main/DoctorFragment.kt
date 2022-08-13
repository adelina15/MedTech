package com.example.medtech.view.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.medtech.R
import com.example.medtech.databinding.FragmentDoctorBinding
import com.example.medtech.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DoctorFragment : Fragment() {

    private val args by navArgs<DoctorFragmentArgs>()
    private var _binding: FragmentDoctorBinding? = null
    private val binding
        get() = _binding!!
    private val userViewModel by viewModel<UserViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_doctor, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_arrow)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
        with(binding) {
            experience.text = args.doctor.work_experience
            doctorName.text = "${args.doctor.first_name} ${args.doctor.last_name}"
            education.text = args.doctor.education
            dipoms.text = args.doctor.achievements
            if (args.doctor.image != null) Glide.with(requireContext()).load(args.doctor.image).into(doctorImage)
        }
        userViewModel.errorMessage.observe(requireActivity()) {
            Log.i("scheduleError", it)
            Toast.makeText(requireContext(), "Что-то пошло не так", Toast.LENGTH_SHORT).show()
        }
    }

}