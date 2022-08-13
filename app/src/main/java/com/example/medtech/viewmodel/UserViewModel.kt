package com.example.medtech.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medtech.data.model.Doctor
import com.example.medtech.data.model.User
import com.example.medtech.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel (private val repository: UserRepository): ViewModel(),
    DefaultLifecycleObserver {

    val user = MutableLiveData<User>()
    val doctor = MutableLiveData<Doctor>()
    val errorMessage = MutableLiveData<String>()

    fun getProfileById(id: Int) {
        viewModelScope.launch {
            val response = repository.getUserById(id)
            if (response.isSuccessful) {
                user.postValue(response.body())
            }
            else{
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }

}