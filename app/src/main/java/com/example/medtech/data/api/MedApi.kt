package com.example.medtech.data.api

import com.example.medtech.data.model.BabyItem
import com.example.medtech.data.model.Picture
import com.example.medtech.data.model.Token
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

    @GET("api/pictures/{id}/")
    suspend fun getPictureById(
        @Path("id") id: Int?
    ): Response<Picture>

}