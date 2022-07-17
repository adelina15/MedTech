package com.example.medtech.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.medtech.R
import com.example.medtech.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myDocButton.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToDoctorFragment()
            findNavController().navigate(action)
        }
        binding.callHospital.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$+996123456"))
            startActivity(intent)
        }
        binding.wa.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(
                        "https://api.whatsapp.com/send?phone=996700070878 Number&text=Здравствуйте!")))
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}