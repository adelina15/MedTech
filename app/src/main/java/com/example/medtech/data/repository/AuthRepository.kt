package com.example.medtech.data.repository

import com.example.medtech.data.api.MedApi
import com.example.medtech.data.model.BabyItem
import com.example.medtech.data.model.Token
import com.example.medtech.data.model.User
import retrofit2.Response

class AuthRepository constructor (private val medApi: MedApi) {
    suspend fun getToken(number: String): Response<Token> {
        return medApi.getToken(number)
    }

    suspend fun getBabyByWeek(id: Int): Response<BabyItem>{
        return medApi.getBabyByWeek(id)
    }
}