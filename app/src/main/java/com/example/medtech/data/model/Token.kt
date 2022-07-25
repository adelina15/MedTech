package com.example.medtech.data.model

data class Token(
    val access: String,
    val isSuperuser: Boolean,
    val refresh: String,
    val status: String,
    val user_type: String,
    val user_id: Int
)