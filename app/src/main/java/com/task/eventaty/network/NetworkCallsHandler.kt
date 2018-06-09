package com.task.eventaty.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

/**
 * @author Mohamed Saleh on 6/7/2018.
 */
class NetworkCallsHandler {

    val httpClient = OkHttpClient.Builder()

    val retrofitBuilder = Retrofit.Builder().baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    val retrofit = retrofitBuilder.client(httpClient.build()).build()

    fun <T: Any> createService(clazz: KClass<T>): T {
        return retrofit.create(clazz.java)
    }
}