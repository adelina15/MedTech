package com.example.medtech.data.api

import com.example.medtech.data.model.*
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface MedApi {
    @FormUrlEncoded
    @POST("accounts/login/mob/")
    suspend fun getToken(
        @Field("phone") phone: String,
    ): Response<Token>

    @FormUrlEncoded
    @POST("api/get-free-time/")
    suspend fun getFreeTime(
        @Field("doctor") doctor: Int,
        @Field("date") date: String
    ): Response<TimeSlot>

    @FormUrlEncoded
    @POST("api/appointment/")
    suspend fun appointment(
//        @Body appointment: Appointment
        @Field("record") record: String,
        @Field("description") description: String,
        @Field("date") date: String,
        @Field("patient") patient: Int,
        @Field("doctor") doctor: Int,
        @Field("time_slots") time_slots: Int,
    )

    @GET("api/handbook/{id}/")
    suspend fun getBabyByWeek(
        @Path("id") id: Int?
    ): Response<BabyItem>

    @GET("api/patient/{id}/")
    suspend fun getUserById(
        @Path("id") id: Int?
    ): Response<User>

    @GET("api/check-list/")
    suspend fun getChecklist(
        @Query("patient") id: Int?,
        @Query("month") month: Int?,
    ): Response<ArrayList<Checklist>>

    @GET("api/article/")
    suspend fun getArticles(): Response<Array<Article>>

    @GET("api/FAQ/")
    suspend fun getFaq(): Response<Array<Faq>>

}