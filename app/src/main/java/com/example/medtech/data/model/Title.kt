package com.example.medtech.data.model

data class Title(
    val id: Int,
    val name: String,
    val question: List<Question>
)