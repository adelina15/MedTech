package com.example.medtech.data.model

data class Patient(
    val date_of_pregnancy: String,
    val id: Int,
    val inn: String,
    val month_of_pregnancy: Int,
    val week_of_pregnancy: Int
)