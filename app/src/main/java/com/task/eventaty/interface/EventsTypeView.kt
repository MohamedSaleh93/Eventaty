package com.task.eventaty.`interface`

import com.task.eventaty.model.SubEventTypeModel

/**
 * @author Mohamed Saleh on 6/7/2018.
 */
interface EventsTypeView {
    fun onEventsLoadedSuccessfully(eventsItems: ArrayList<SubEventTypeModel>?)
    fun onEventsLoadedFailed()
}