package com.example.medtech.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doctor(
    val achievements: String,
    val address: String,
    val age: Int,
    val birth_date: String,
    val education: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val image: String?,
    val last_name: String,
    val patient: List<Int>,
    val phone: String,
    val professional_sphere: String,
    val resign: String,
    val user_type: String,
    val work_experience: String
) : Parcelable