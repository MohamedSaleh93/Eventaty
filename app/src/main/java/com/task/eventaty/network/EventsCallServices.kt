package com.task.eventaty.network

import com.task.eventaty.model.EventTypeModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author Mohamed Saleh on 6/7/2018.
 */
interface EventsCallServices {

    @GET("eventtypes")
    fun getEventsType(): Call<ArrayList<EventTypeModel>>

}