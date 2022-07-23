package com.example.medtech.data.model

import java.io.Serializable

data class BabyItem(
    val advices: String,
    val baby_img: String,
    val content: String,
    val fruit_img: String,
    val height: String,
    val id: Int,
    val title: String,
    val week: Int,
    val weight: String
): Serializable