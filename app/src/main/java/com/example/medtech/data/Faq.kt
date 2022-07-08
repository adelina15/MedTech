package com.example.medtech.data

data class Faq(
    val question: String,
    val answer: String,
    var expanded: Boolean = false
)
