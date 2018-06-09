package com.task.eventaty.ui

import android.app.ActionBar
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.task.eventaty.R
import com.task.eventaty.model.EventTypeModel
import com.task.eventaty.utils.Constants
import kotlinx.android.synthetic.main.activity_events.*

/**
 * @author Mohamed Saleh on 6/7/2018.
 */
class EventsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        var eventsList: ArrayList<EventTypeModel> = intent.getParcelableArrayListExtra(Constants.EVENTS_LIST_KEY)

        for (eventItem in eventsList) {
            tabLayout.addTab(tabLayout.newTab().setText(eventItem.eventName))
        }

        tabLayout.getTabAt(0)?.select()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                fragmentManager.beginTransaction().
                        replace(R.id.fragmentsContainer,
                                EventsTabsFragment.getFragmentObject
                                (eventsList.get(tab?.position!!))).commit()
            }

        })
    }
}