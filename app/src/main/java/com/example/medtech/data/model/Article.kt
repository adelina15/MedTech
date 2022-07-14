package com.example.medtech.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val title: String,
    val image: Int,
    val subtitle: String,
    val text: String
): Parcelable
