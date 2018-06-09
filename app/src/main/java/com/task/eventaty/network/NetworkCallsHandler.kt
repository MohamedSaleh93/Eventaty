package com.task.eventaty.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

/**
 * @author Mohamed Saleh on 6/7/2018.
 */
class NetworkCallsHandler {

    private fun getHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        //TODO handle based on currently defined build variants
//        if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY // BASIC
        httpClient.addInterceptor(logging)
//        }
        return httpClient.build()
    }

    val retrofitBuilder = Retrofit.Builder().baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    val retrofit = retrofitBuilder.client(getHttpClient()).build()

    fun <T: Any> createService(clazz: KClass<T>): T {
        return retrofit.create(clazz.java)
    }
}