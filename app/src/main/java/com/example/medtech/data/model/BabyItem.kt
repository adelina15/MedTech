package com.example.medtech.data.model

import java.io.Serializable

data class BabyItem(
    val advices: String,
    val content: String,
    val height: String,
    val id: Int,
    val pictures: List<Int>,
    val title: String,
    val week: Int,
    val weight: String
): Serializable