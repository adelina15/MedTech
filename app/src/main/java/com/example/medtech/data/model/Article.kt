package com.example.medtech.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val title: String,
    val pictures: String,
    val id: Int,
    val content: String
): Parcelable
