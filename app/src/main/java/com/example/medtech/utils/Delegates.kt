package com.example.medtech.utils

import com.example.medtech.data.Article
import com.example.medtech.data.Faq
import com.example.medtech.data.Week

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
}