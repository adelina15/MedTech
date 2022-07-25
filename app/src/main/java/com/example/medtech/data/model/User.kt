package com.example.medtech.data.model

data class User(
    val address: String,
    val age: Int,
    val birth_date: String,
    val date_joined: String,
    val email: String?,
    val first_name: String,
    val id: Int,
    val image: String?,
    val last_name: String,
    val patient: Patient,
    val phone: String
)