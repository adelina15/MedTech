package com.example.medtech.koin

import com.example.medtech.data.api.MedApi
import com.example.medtech.data.repository.AuthRepository
import com.example.medtech.data.repository.InfoRepository
import com.example.medtech.data.repository.UserRepository
import com.example.medtech.utils.Constants
import com.example.medtech.viewmodel.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {

    single { getOkHttp() }
    single { getRetrofitInstance(okHttpClient = get()) }
    single { getMedApi(retrofit = get()) }
    factory { AuthRepository(medApi = get()) }
    factory { UserRepository(medApi = get()) }
    factory { InfoRepository(medApi = get()) }
}
val viewModules = module {
    viewModel { AuthViewModel(repository = get()) }
    viewModel { BabyViewModel(repository = get()) }
    viewModel { UserViewModel(repository = get()) }
    viewModel { ArticlesViewModel(repository = get()) }
    viewModel { FaqViewModel(repository = get()) }
}

fun getOkHttp(): OkHttpClient {
    return OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
        .build()
}

fun getMedApi(retrofit: Retrofit): MedApi {
    return retrofit.create(MedApi::class.java)
}

fun getRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}