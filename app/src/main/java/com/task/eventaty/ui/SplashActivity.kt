package com.task.eventaty.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.task.eventaty.R
import com.task.eventaty.`interface`.SplashView
import com.task.eventaty.model.EventTypeModel
import com.task.eventaty.presenter.SplashPresenterImp
import com.task.eventaty.utils.Constants
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * @author Mohamed Saleh on 6/7/2018.
 */
class SplashActivity: AppCompatActivity(), SplashView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var splashPresenter = SplashPresenterImp(this)
        splashPresenter.initSplash()
    }

    override fun onViewCreatedSuccessfully(eventsTypeList: ArrayList<EventTypeModel>?) {
        var intent = Intent(this, EventsActivity::class.java)
        intent.putParcelableArrayListExtra(Constants.EVENTS_LIST_KEY, eventsTypeList)
        startActivity(intent)
        finish()
    }

    override fun onViewCreatedFailed() {
        Toast.makeText(this, "An error acquired Please try again later", Toast.LENGTH_LONG).show()
        loadingProgressBar.visibility = View.GONE
    }
}