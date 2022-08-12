package com.example.medtech.data.model

data class User(
    val address: String,
    val approximate_date_of_pregnancy: String,
    val birth_date: String,
    val date_of_pregnancy: String,
    val doctor_field: Int,
    val first_name: String,
    val id: Int,
    val image: String?,
    val inn: String,
    val last_name: String,
    val month_of_pregnancy: Int,
    val phone: String,
    val week_of_pregnancy: Int
)