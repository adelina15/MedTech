package com.example.medtech.viewmodel

import android.content.ClipDescription
import androidx.lifecycle.*
import com.example.medtech.data.model.Appointment
import com.example.medtech.data.model.TimeSlot
import com.example.medtech.data.repository.ScheduleRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.text.SimpleDateFormat
import java.util.*

class ScheduleViewModel (private val repository: ScheduleRepository): ViewModel(),
    DefaultLifecycleObserver {

    val freeTime = MutableLiveData<TimeSlot>()
    val errorMessage = MutableLiveData<Error>()
    var selectedDate = MutableLiveData<String>()

    fun getFreeTime(doctor:Int, date: String) {
        viewModelScope.launch {
            val response = repository.getFreeTime(doctor, date)
            try{
                if (response.isSuccessful) {
                    freeTime.postValue(response.body())
                }
            } catch (ex: HttpException){
                if (ex.code() == 400){
                    val gson = Gson()
                    val message = gson.fromJson(ex.response()?.body().toString(), Error::class.java )
                    errorMessage.postValue(message)
                }
                else errorMessage.postValue(Error("Что-то пошло не так"))
            }
        }
    }

    fun setDate(date: String){
        selectedDate.value = date
    }

    fun makeAppointment(description: String, record: String, timeSlots: Int, doctor: Int, patient: Int) {
        viewModelScope.launch {
            repository.makeAppointment(Appointment(selectedDate.value!!, description, patient, doctor, record, timeSlots))
        }
    }
}

data class Error(val response: String)
