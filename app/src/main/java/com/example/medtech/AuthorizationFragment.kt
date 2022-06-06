package com.example.medtech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.medtech.databinding.FragmentAuthorizationBinding
import com.google.firebase.auth.FirebaseAuth

class AuthorizationFragment : Fragment() {

    lateinit var binding: FragmentAuthorizationBinding
    var number: String = ""
    lateinit var auth: FirebaseAuth

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
        number = binding.editTextPhone.text.trim().toString()
        if (number.isNotEmpty() && number.length == 13) {
            val action = AuthorizationFragmentDirections.actionAuthorizationFragmentToCodeFragment(number)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Введите номер телефона", Toast.LENGTH_SHORT).show()
        }
    }
}
