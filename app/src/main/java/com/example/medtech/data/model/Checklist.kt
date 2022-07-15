package com.example.medtech.data.model

data class Checklist(
    val question: String,
    val isOk: Boolean = true,
    val comment: String? = null
)
