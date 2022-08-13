package com.example.medtech.viewmodel

import androidx.lifecycle.*
import com.example.medtech.data.model.Faq
import com.example.medtech.data.repository.InfoRepository
import kotlinx.coroutines.launch

class FaqViewModel(private val repository: InfoRepository): ViewModel(), DefaultLifecycleObserver {

    val faq = MutableLiveData<Array<Faq>>()
    val errorMessage = MutableLiveData<String>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        getFaq()
    }

    private fun getFaq() {
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