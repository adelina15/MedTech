package com.example.medtech.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medtech.data.model.BabyItem
import com.example.medtech.data.model.Token
import com.example.medtech.data.repository.AuthRepository
import kotlinx.coroutines.launch

class BabyViewModel (private val repository: AuthRepository): ViewModel(),
    DefaultLifecycleObserver {

    val baby = MutableLiveData<BabyItem>()
    val errorMessage = MutableLiveData<String>()

    fun getBabyById(id: Int) {
        viewModelScope.launch {
            val response = repository.getBabyByWeek(id)
            if (response.isSuccessful) {
                baby.postValue(response.body())
            }
            else{
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }
}