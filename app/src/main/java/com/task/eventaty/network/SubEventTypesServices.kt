package com.task.eventaty.network

import com.task.eventaty.model.SubEventTypeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Mohamed Saleh on 6/7/2018.
 */
interface SubEventTypesServices {

    @GET("events")
    fun getSubEventByTypeAndPage(@Query("event_type") eventType: String,
                                 @Query("page") pageNumber: Int): Call<ArrayList<SubEventTypeModel>>
}