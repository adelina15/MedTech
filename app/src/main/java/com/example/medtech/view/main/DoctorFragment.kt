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
        userViewModel.getDoctorById(args.doctorId)
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
        userViewModel.doctor.observe(requireActivity()) {
            with(binding){
                experience.text = it.work_experience
                doctorName.text = "${it.first_name} ${it.last_name}"
                education.text = it.education
                dipoms.text = it.achievements
                if(it.image != null) Glide.with(requireContext()).load(it.image).into(doctorImage)
            }
        }
        userViewModel.errorMessage.observe(requireActivity()) {
            Log.i("profile", it)
            Toast.makeText(requireContext(), "Что-то пошло не так", Toast.LENGTH_SHORT).show()
        }
    }

}