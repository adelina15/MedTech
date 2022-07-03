package com.example.medtech.utils

import com.example.medtech.data.Week

interface Delegates {
    interface WeekClicked{
        fun onItemClick(week: Week)
    }
}