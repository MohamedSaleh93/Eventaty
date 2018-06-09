package com.task.eventaty.model

import com.google.gson.annotations.SerializedName

/**
 * @author Mohamed Saleh on 6/7/2018.
 */
class SubEventTypeModel {

    companion object {
        enum class EVENT_TYPE_IN_LIST {
            MESSAGE,
            LOADING
        }
    }

    var longitude: Double = 0.0
    var latitude: Double = 0.0
    @SerializedName("end_date")
    lateinit var endDate: String
    @SerializedName("start_date")
    lateinit var startDate: String
    @SerializedName("cover")
    lateinit var coverImageUrl: String
    lateinit var name: String
    lateinit var id: String
    var eventTypeInList: EVENT_TYPE_IN_LIST = EVENT_TYPE_IN_LIST.MESSAGE
}