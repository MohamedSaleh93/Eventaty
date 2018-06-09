package com.task.eventaty.presenter

import com.task.eventaty.`interface`.EventsTypeView
import com.task.eventaty.model.EventTypeModel
import com.task.eventaty.model.SubEventTypeModel
import com.task.eventaty.network.NetworkCallsHandler
import com.task.eventaty.network.SubEventTypesServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Mohamed Saleh on 6/7/2018.
 */
class EventsTabFragmentPresenterImp(private val eventsView: EventsTypeView): EventsTabsFragmentPresenter {

    override fun loadEventsTypes(baseEventItemName: String, pageNumber: Int) {
        val subEventsCall = NetworkCallsHandler().
                createService(SubEventTypesServices::class).
                getSubEventByTypeAndPage(baseEventItemName, pageNumber)
        subEventsCall.enqueue(object : Callback<ArrayList<SubEventTypeModel>> {
            override fun onFailure(call: Call<ArrayList<SubEventTypeModel>>?, t: Throwable?) {
                eventsView.onEventsLoadedFailed()
            }

            override fun onResponse(call: Call<ArrayList<SubEventTypeModel>>?,
                                    response: Response<ArrayList<SubEventTypeModel>>?) {
                if (response?.body() != null) {
                    eventsView.onEventsLoadedSuccessfully(response.body())
                } else {
                    eventsView.onEventsLoadedFailed()
                }
            }
        })
    }

}