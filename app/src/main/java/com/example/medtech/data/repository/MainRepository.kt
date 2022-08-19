package com.example.medtech.data.repository

import android.util.Log
import com.example.medtech.data.api.MedApi
import com.example.medtech.data.model.BabyItem
import com.example.medtech.data.model.Checklist
import com.example.medtech.data.model.Token
import retrofit2.Response

class MainRepository (private val medApi: MedApi) {

    suspend fun getBabyByWeek(id: Int): Response<BabyItem> {
        Log.d("authRepo", medApi.getBabyByWeek(id).toString())
        return medApi.getBabyByWeek(id)
    }

    suspend fun getChecklist(id: Int, month: Int): Response<ArrayList<Checklist>> {
        return medApi.getChecklist(id, month)
    }

}