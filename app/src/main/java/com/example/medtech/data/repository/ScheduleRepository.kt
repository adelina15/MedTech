package com.example.medtech.data.repository

import com.example.medtech.data.api.MedApi
import com.example.medtech.data.model.Appointment
import com.example.medtech.data.model.Doctor
import com.example.medtech.data.model.TimeSlot
import retrofit2.Response
import java.util.*

class ScheduleRepository (private val medApi: MedApi) {
    suspend fun getFreeTime(doctor: Int, date: String): Response<TimeSlot> {
        return medApi.getFreeTime(doctor, date)
    }

    suspend fun makeAppointment(appointment: Appointment) {
//        return medApi.appointment(appointment.record, appointment.description, appointment.patient, appointment.doctor, appointment.date, appointment.time_slots )
        return medApi.appointment(appointment)
    }
}