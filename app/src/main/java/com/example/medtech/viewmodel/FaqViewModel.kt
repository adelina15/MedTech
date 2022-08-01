package com.example.medtech.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medtech.data.model.Article
import com.example.medtech.data.model.Faq
import com.example.medtech.data.repository.InfoRepository
import kotlinx.coroutines.launch

class FaqViewModel(private val repository: InfoRepository): ViewModel(), DefaultLifecycleObserver {

    val faq = MutableLiveData<Array<Faq>>()
    val errorMessage = MutableLiveData<String>()

    fun getFaq() {
        viewModelScope.launch {
            val response = repository.getFaq()
            if (response.isSuccessful) {
                faq.postValue(response.body())
            }
            else{
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }

}