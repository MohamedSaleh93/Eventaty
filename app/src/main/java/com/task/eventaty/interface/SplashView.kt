package com.task.eventaty.`interface`

import com.task.eventaty.model.EventTypeModel

/**
 * @author Mohamed Saleh on 6/7/2018.
 */
interface SplashView {
    fun onViewCreatedSuccessfully(eventsTypeList: ArrayList<EventTypeModel>?)
    fun onViewCreatedFailed()
}