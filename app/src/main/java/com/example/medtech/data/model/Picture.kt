package com.example.medtech.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Picture(
    val id: Int,
    val image: String,
    val title: String
): Parcelable