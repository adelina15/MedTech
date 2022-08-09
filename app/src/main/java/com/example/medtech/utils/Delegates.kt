package com.example.medtech.utils

import com.example.medtech.data.model.*

interface Delegates {
    interface WeekClicked{
        fun onItemClick(week: Week)
    }
    interface ArticleClicked{
        fun onItemClick(article: Article)
    }
    interface HourClicked{
        fun onItemClick(hour: Time)
    }
}