package com.example.medtech.data.model

data class TimeSlot(
    val booked_times: List<Time>,
    val date: String,
    val doctor: String,
    val free_times: List<Time>
)