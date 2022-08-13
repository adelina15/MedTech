package com.example.medtech.viewmodel

import android.content.ClipDescription
import androidx.lifecycle.*
import com.example.medtech.data.model.Appointment
import com.example.medtech.data.model.TimeSlot
import com.example.medtech.data.repository.ScheduleRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ScheduleViewModel (private val repository: ScheduleRepository): ViewModel(),
    DefaultLifecycleObserver {

    val freeTime = MutableLiveData<TimeSlot>()
    val errorMessage = MutableLiveData<String>()
    var selectedDate = MutableLiveData<String>()

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

    fun setDate(date: String){
        selectedDate.value = date
    }

    fun makeAppointment(description: String, record: String, timeSlots: Int) {
        viewModelScope.launch {
            repository.makeAppointment(Appointment(selectedDate.value!!, description, record, timeSlots))
//            if (response.isSuccessful) {
//                freeTime.postValue(response.body())
//            }
//            else{
//                errorMessage.postValue(response.errorBody().toString())
//            }
        }
    }
}
