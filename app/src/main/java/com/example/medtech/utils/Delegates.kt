package com.example.medtech.utils

import com.example.medtech.data.model.Article
import com.example.medtech.data.model.Faq
import com.example.medtech.data.model.Hour
import com.example.medtech.data.model.Week

interface Delegates {
    interface WeekClicked{
        fun onItemClick(week: Week)
    }
    interface ArticleClicked{
        fun onItemClick(article: Article)
    }
    interface FaqClicked{
        fun onItemClick(faq: Faq)
    }
    interface HourClicked{
        fun onItemClick(hour: Hour)
    }
}