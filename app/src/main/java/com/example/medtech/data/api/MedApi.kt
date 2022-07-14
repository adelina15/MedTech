package com.example.medtech.data.api

import com.example.medtech.data.model.Token
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MedApi {
    @FormUrlEncoded
    @POST("accounts/login_mob/")
    suspend fun getToken(
        @Field("phone") phone: String,
    ): Response<Token>
}