package com.example.medtech.data.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class BabyItem(
    val advices: String,
    val baby_img: String,
    val content: String,
    val fruit_img: String,
    val height: String,
    val title: String,
    val week: Int,
    val weight: String,
    val food: String,
    val dates_of_advices: String? = null,
    val mom_weight: String
): Parcelable