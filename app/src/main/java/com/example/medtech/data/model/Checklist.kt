package com.example.medtech.data.model

data class Checklist(
    val answer: List<Answer>,
    val id: Int,
    val is_archive: Boolean,
    val month: Int,
    val patient: Int,
    val recommendations: String? = null,
    val template: Template
)
