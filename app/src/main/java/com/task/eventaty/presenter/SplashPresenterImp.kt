package com.task.eventaty.presenter

import com.task.eventaty.`interface`.SplashView
import com.task.eventaty.network.EventsCallServices
import com.task.eventaty.network.NetworkCallsHandler
import retrofit2.Callback
import com.task.eventaty.model.EventTypeModel
import retrofit2.Call
import retrofit2.Response


/**
 * @author Mohamed Saleh on 6/7/2018.
 */
class SplashPresenterImp(private var splashView: SplashView): SplashPresenter {

    override fun initSplash() {
        val eventTypesCall = NetworkCallsHandler().createService(EventsCallServices::class).getEventsType()
        eventTypesCall.enqueue(object : Callback<ArrayList<EventTypeModel>> {
            override fun onFailure(call: Call<ArrayList<EventTypeModel>>?, t: Throwable?) {
                splashView.onViewCreatedFailed()
            }

            override fun onResponse(call: Call<ArrayList<EventTypeModel>>?,
                                    response: Response<ArrayList<EventTypeModel>>?) {
                if (response?.body() != null) {
                    splashView.onViewCreatedSuccessfully(response.body())
                } else {
                    splashView.onViewCreatedFailed()
                }
            }
        })
    }

}