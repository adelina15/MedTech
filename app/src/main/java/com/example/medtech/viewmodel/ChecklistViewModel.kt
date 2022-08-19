package com.example.medtech.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medtech.data.model.BabyItem
import com.example.medtech.data.model.Checklist
import com.example.medtech.data.repository.MainRepository
import kotlinx.coroutines.launch

class ChecklistViewModel (private val repository: MainRepository): ViewModel(),
    DefaultLifecycleObserver {

    val checklist = MutableLiveData<ArrayList<Checklist>>()
    val errorMessage = MutableLiveData<String>()

    fun getChecklist(id: Int, month: Int) {
        viewModelScope.launch {
            val response = repository.getChecklist(id, month)
            if (response.isSuccessful) {
                checklist.postValue(response.body())
            }
            else{
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }

}