package com.example.medtech.data.model

data class Doctor(
    val achievements: String,
    val address: String,
    val birth_date: String,
    val education: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val image: Any,
    val last_name: String,
    val patient: List<Int>,
    val phone: String,
    val professional_sphere: String,
    val resign: String,
    val work_experience: String
)