package com.task.eventaty.presenter

import com.task.eventaty.model.EventTypeModel

/**
 * @author Mohamed Saleh on 6/7/2018.
 */
interface EventsTabsFragmentPresenter {

    fun loadEventsTypes(baseEventItemName: String, pageNumber: Int)
}