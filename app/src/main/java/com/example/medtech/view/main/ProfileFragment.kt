package com.example.medtech.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.auth0.android.jwt.Claim
import com.auth0.android.jwt.JWT
import com.bumptech.glide.Glide
import com.example.medtech.R
import com.example.medtech.data.UserPreferences
import com.example.medtech.data.model.Doctor
import com.example.medtech.databinding.FragmentProfileBinding
import com.example.medtech.view.auth.AuthorizationFragmentDirections
import com.example.medtech.viewmodel.AuthViewModel
import com.example.medtech.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = _binding!!
    private val userViewModel by viewModel<UserViewModel>()
    private lateinit var sharedPreferences: UserPreferences
    private lateinit var doctor: Doctor
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        sharedPreferences = UserPreferences(requireContext())
        userViewModel.getProfileById(sharedPreferences.fetchUserId())
        Log.i("profile", sharedPreferences.fetchUserId().toString())
        setupObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myDocButton.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToDoctorFragment(doctor)
            findNavController().navigate(action)
        }
        binding.callHospital.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$+996123456"))
            startActivity(intent)
        }
        binding.wa.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW, Uri.parse(
                        "https://api.whatsapp.com/send?phone=996700070878 Number&text=Здравствуйте!"
                    )
                )
            )
        }
    }

    private fun setupObservers() {
        userViewModel.user.observe(requireActivity()) {
            with(binding) {
                idNumber.text = it.inn
                name.text = "${it.first_name} ${it.last_name}"
                dateOfBirth.text = it.birth_date
                address.text = it.address
                age.text = "${it.age} лет"
                phoneNumber.text = it.phone
                if (it.image != null) Glide.with(requireContext()).load(it.image).into(image)
                doctor = it.doctor_field
            }
        }
        userViewModel.errorMessage.observe(requireActivity()) {
            Log.i("profile", it)
            Toast.makeText(requireContext(), "Что-то пошло не так", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}