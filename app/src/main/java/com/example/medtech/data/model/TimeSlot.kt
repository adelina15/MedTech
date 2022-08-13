package com.example.medtech.data.model

import retrofit2.http.Field

data class TimeSlot(
    val booked_times: List<Time>,
    val date: String,
    val doctor: String,
    val free_times: List<Time>?
)