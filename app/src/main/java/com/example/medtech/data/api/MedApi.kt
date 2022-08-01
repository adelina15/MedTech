package com.example.medtech.data.api

import com.example.medtech.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface MedApi {
    @FormUrlEncoded
    @POST("accounts/login_mob/")
    suspend fun getToken(
        @Field("phone") phone: String,
    ): Response<Token>

    @GET("api/handbook/{id}/")
    suspend fun getBabyByWeek(
        @Path("id") id: Int?
    ): Response<BabyItem>

    @GET("api/patient-profile/{id}/")
    suspend fun getUserById(
        @Path("id") id: Int?
    ): Response<User>

    @GET("api/article/")
    suspend fun getArticles(): Response<Array<Article>>

    @GET("api/FAQ/")
    suspend fun getFaq(): Response<Array<Faq>>

}