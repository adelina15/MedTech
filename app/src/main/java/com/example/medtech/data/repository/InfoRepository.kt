package com.example.medtech.data.repository

import com.example.medtech.data.api.MedApi
import com.example.medtech.data.model.Article
import com.example.medtech.data.model.Faq
import retrofit2.Response

class InfoRepository constructor (private val medApi: MedApi) {
    suspend fun getArticles(): Response<Array<Article>> {
        return medApi.getArticles()
    }
    suspend fun getFaq(): Response<Array<Faq>> {
        return medApi.getFaq()
    }
}