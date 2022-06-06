package com.example.medtech

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.medtech.databinding.FragmentCodeBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class CodeFragment : Fragment() {
    private val args: CodeFragmentArgs by navArgs()
    lateinit var auth: FirebaseAuth
    private var verificationCode: String = ""
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private val number by lazy { args.number }
    private var _binding: FragmentCodeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_code, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
//        binding.phoneNumber.text = number
//        startTimer()

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            // Этот метод вызывается после завершения проверки
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                if(credential.smsCode != null){
                    binding.editTextCode.setText(credential.smsCode)
                }
                startActivity(Intent(requireContext(), MainActivity::class.java))
                Log.d("Code", "Верификация прошла успешно")
            }

            // Вызывается, когда проверка не удалась
            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                Log.d("Code", "Верификация не удалась $e")
            }

            // При отправке кода Firebase вызывается этот метод
            //здесь мы начинаем новую активность, в которой пользователь может ввести OTP
            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                verificationCode = p0
                Toast.makeText(requireContext(), "Сообщение отправленно", Toast.LENGTH_LONG).show()
            }
        }

        sendVerificationCode()

        // заполняем otp и вызывем по нажатии на кнопку
        binding.getCodeButton.setOnClickListener {
            val code = binding.editTextCode.text?.trim().toString()
            if(code.isNotEmpty()){
                val credential  = PhoneAuthProvider.getCredential(verificationCode, code)
                signInWithPhoneAuthCredential(credential)
            }else{
                Toast.makeText(requireContext(),"Enter OTP", Toast.LENGTH_SHORT).show()
            }
        }

//        binding.sendAgainButton.setOnClickListener {
//            resendOTP(requireActivity(), number)
//            startTimer()
//        }

//        binding.linearLayoutBack.setOnClickListener {
//            onBackPressed()
//        }

//        with(binding.toolbar) {
//            setNavigationIcon(R.drawable.ic_back_arrow)
//            setNavigationOnClickListener {
//                val action = CodeFragmentDirections.actionCodeFragmentToLoginFragment()
//                findNavController().navigate(action)
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // этот метод отправляет код подтверждения и запускает обратный вызов проверки
    // который реализован выше в onCreate
    private fun sendVerificationCode() {
//        PhoneAuthOptions.newBuilder()
//            .setActivity(requireActivity())
//            .setPhoneNumber(number)
//            .setTimeout(60L, TimeUnit.SECONDS)
//            .setCallbacks(callbacks)
//            .build()
//            .apply {
//                PhoneAuthProvider.verifyPhoneNumber(this)
//            }
        val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
            .setPhoneNumber(number)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    // проверяем, соответствует ли код, отправленный firebase
    // в случае успеха запускаем новое activity
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                val intent = Intent(requireContext() , MainActivity::class.java)
                startActivity(intent)
            } else {
                // если ошибка входа, отобразится сообщение
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    // Введенный код подтверждения недействителен
                    Toast.makeText(requireContext(),"Неверный код", Toast.LENGTH_SHORT).show()
//                    binding.wrongCode.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun resendOTP(activity: Activity, number: String){
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .setForceResendingToken(resendToken) // ForceResendingToken from callbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    // Зпуск таймера чтобы пользователь за 60 сек ввел смс код
//    private fun startTimer() {
//        object : CountDownTimer(60000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                binding.timer.text = "Отправить код повторно через: 0:${millisUntilFinished / 1000}"
//            }
//
//            override fun onFinish() {
//                binding.timer.text = " "
//                binding.sendAgainButton.visibility = View.VISIBLE
//            }
//        }.start()
//    }
}