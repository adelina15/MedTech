package com.example.medtech.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medtech.data.model.TimeSlot
import com.example.medtech.data.repository.ScheduleRepository
import kotlinx.coroutines.launch
import java.util.*

class ScheduleViewModel (private val repository: ScheduleRepository): ViewModel(),
    DefaultLifecycleObserver {

    val freeTime = MutableLiveData<TimeSlot>()
    val errorMessage = MutableLiveData<String>()

    fun getFreeTime(doctor:Int, date: String) {
        viewModelScope.launch {
            val response = repository.getFreeTime(doctor, date)
            if (response.isSuccessful) {
                freeTime.postValue(response.body())
            }
            else{
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }
}