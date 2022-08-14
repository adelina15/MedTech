package com.example.medtech.data.model

data class A(
    val answer: List<Answer>,
    val id: Int,
    val is_archive: Boolean,
    val month: Int,
    val patient: Int,
    val recommendations: String,
    val template: Template
)