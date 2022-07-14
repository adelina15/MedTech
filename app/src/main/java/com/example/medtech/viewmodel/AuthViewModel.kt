package com.example.medtech.viewmodel

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medtech.data.model.Token
import com.example.medtech.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel (private val repository: AuthRepository): ViewModel(), DefaultLifecycleObserver {

    val token = MutableLiveData<Token>()
    val errorMessage = MutableLiveData<String>()

    fun getToken(number: String) {
        viewModelScope.launch {
            val response = repository.getToken(number)
            if (response.isSuccessful) {
                token.postValue(response.body())
            }
            else{
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }
}