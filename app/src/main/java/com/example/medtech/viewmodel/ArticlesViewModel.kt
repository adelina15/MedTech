package com.example.medtech.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medtech.data.model.Article
import com.example.medtech.data.model.User
import com.example.medtech.data.repository.InfoRepository
import com.example.medtech.data.repository.UserRepository
import kotlinx.coroutines.launch

class ArticlesViewModel  (private val repository: InfoRepository): ViewModel(), DefaultLifecycleObserver {

    val articles = MutableLiveData<Array<Article>>()
    val errorMessage = MutableLiveData<String>()

    fun getArticles() {
        viewModelScope.launch {
            val response = repository.getArticles()
            if (response.isSuccessful) {
                articles.postValue(response.body())
            }
            else{
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }

}