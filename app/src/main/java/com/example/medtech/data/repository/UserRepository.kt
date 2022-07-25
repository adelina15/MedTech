package com.example.medtech.data.repository

import com.example.medtech.data.api.MedApi
import com.example.medtech.data.model.User
import retrofit2.Response

class UserRepository constructor (private val medApi: MedApi) {
    suspend fun getUserById(id: Int): Response<User> {
        return medApi.getUserById(id)
    }
}