package com.example.medtech.data.repository

import android.util.Log
import com.example.medtech.data.api.MedApi
import com.example.medtech.data.model.Doctor
import com.example.medtech.data.model.User
import retrofit2.Response

class UserRepository constructor (private val medApi: MedApi) {
    suspend fun getUserById(id: Int): Response<User> {
        Log.d("userRepo", medApi.getUserById(id).toString())
        return medApi.getUserById(id)
    }
}