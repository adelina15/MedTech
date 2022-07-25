package com.example.medtech.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medtech.data.model.User
import com.example.medtech.data.repository.AuthRepository
import com.example.medtech.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel (private val repository: AuthRepository): ViewModel(),
    DefaultLifecycleObserver {

    val user = MutableLiveData<User>()
    val errorMessage = MutableLiveData<String>()

    fun getBabyById(id: Int) {
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