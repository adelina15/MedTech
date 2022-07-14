package com.example.medtech.data.model

data class Faq(
    val question: String,
    val answer: String,
    var expanded: Boolean = false
)
