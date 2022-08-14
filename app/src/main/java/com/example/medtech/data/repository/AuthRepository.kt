package com.example.medtech.data.repository

import android.util.Log
import com.example.medtech.data.api.MedApi
import com.example.medtech.data.model.BabyItem
import com.example.medtech.data.model.Token
import retrofit2.Response

class AuthRepository (private val medApi: MedApi) {
    suspend fun getToken(number: String): Response<Token> {
        return medApi.getToken(number)
    }

    suspend fun getBabyByWeek(id: Int): Response<BabyItem>{
        Log.d("authRepo", medApi.getBabyByWeek(id ).toString())
        return medApi.getBabyByWeek(id)
    }
}