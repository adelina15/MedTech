package com.example.medtech.data.repository

import com.example.medtech.data.api.MedApi
import com.example.medtech.data.model.TimeSlot
import retrofit2.Response
import java.util.*

class ScheduleRepository constructor(private val medApi: MedApi) {
    suspend fun getFreeTime(doctor: Int, date: Date): Response<TimeSlot> {
        return medApi.getFreeTime(doctor, date)
    }
}