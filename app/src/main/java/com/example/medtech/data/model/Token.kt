package com.example.medtech.data.model

data class Token(
    val access: String,
    val is_superuser: Boolean,
    val refresh: String,
    val status: String,
    val expires_day: String,
    val user_id: Int
)