package com.example.medtech.data.model

data class Appointment(
    val date: String,
    val description: String,
    val doctor: Int,
    val patient: Int,
    val record: String,
    val time_slots: Int
)