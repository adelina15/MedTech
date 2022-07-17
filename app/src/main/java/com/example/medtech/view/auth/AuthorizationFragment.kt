package com.example.medtech.view.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.medtech.R
import com.example.medtech.databinding.FragmentAuthorizationBinding
import com.example.medtech.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthorizationFragment : Fragment() {

    lateinit var binding: FragmentAuthorizationBinding
    var number: String = ""
    lateinit var auth: FirebaseAuth
    private val authViewModel by viewModel<AuthViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_authorization, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding.getCodeButton.setOnClickListener {
            login()
        }
    }
    private fun login() {
        //получить номер телефона из editText
        number = "+996${binding.editTextPhone.unMaskedText.toString()}"
        if (number.isNotEmpty() && number.length == 13) {
            getToken(number)
        } else {
            Toast.makeText(requireContext(), "Введите номер телефона", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getToken(number: String) {
        authViewModel.getToken(number)
        authViewModel.token.observe(requireActivity()){
            Log.i("authE", it.status)
            val action = AuthorizationFragmentDirections.actionAuthorizationFragmentToCodeFragment(number)
            findNavController().navigate(action)
        }
        authViewModel.errorMessage.observe(requireActivity()){
            Log.i("authE", it)
            Toast.makeText(requireContext(), "Такого номера нет!", Toast.LENGTH_SHORT).show()
        }
    }
}
