package com.task.eventaty.ui

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.task.eventaty.R
import com.task.eventaty.`interface`.EventsTypeView
import com.task.eventaty.adapter.EventsItemsAdapter
import com.task.eventaty.model.EventTypeModel
import com.task.eventaty.model.SubEventTypeModel
import com.task.eventaty.presenter.EventsTabFragmentPresenterImp
import com.task.eventaty.utils.Constants
import kotlinx.android.synthetic.main.fragment_events.*

/**
 * @author Mohamed Saleh on 6/7/2018.
 */
class EventsTabsFragment(): Fragment(), EventsTypeView {

    companion object {
        fun getFragmentObject(eventItem: EventTypeModel): EventsTabsFragment {
            val eventTabFragment = EventsTabsFragment()
            val args = Bundle()
            args.putParcelable(Constants.EVENT_ITEM_KEY, eventItem)
            eventTabFragment.arguments = args
            return eventTabFragment
        }
    }

    private lateinit var eventsListAdapter: EventsItemsAdapter
    private var eventsItemsList: ArrayList<SubEventTypeModel>? = ArrayList<SubEventTypeModel>()
    private var pageNumber: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_events, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val eventItem: EventTypeModel = arguments.getParcelable(Constants.EVENT_ITEM_KEY)
        val eventTabPresenter = EventsTabFragmentPresenterImp(this)
        eventsListAdapter = EventsItemsAdapter(eventsItemsList, activity)
        val layoutManager = LinearLayoutManager(activity)
        eventsItemsRecyclerView.layoutManager = layoutManager
        eventsItemsRecyclerView.adapter = eventsListAdapter
        eventTabPresenter.loadEventsTypes(eventItem.eventName, pageNumber)
        eventsItemsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentItemPosition = layoutManager.findLastVisibleItemPosition()
                if (currentItemPosition == eventsItemsList?.size!! - 1 &&
                        eventsItemsList?.get(currentItemPosition)?.eventTypeInList ==
                        SubEventTypeModel.Companion.EVENT_TYPE_IN_LIST.MESSAGE) {
                    var subEventTypeModel = SubEventTypeModel()
                    subEventTypeModel.eventTypeInList = SubEventTypeModel.Companion.EVENT_TYPE_IN_LIST.LOADING
                    eventsItemsList?.add(subEventTypeModel)
                    eventsListAdapter.notifyDataSetChanged()
                    eventTabPresenter.loadEventsTypes(eventItem.eventName, pageNumber)
                }
            }
        })
    }

    override fun onEventsLoadedSuccessfully(eventsItems: ArrayList<SubEventTypeModel>?) {
        loadingProgressBar.visibility = View.GONE
        eventsItemsRecyclerView.visibility = View.VISIBLE
        if (pageNumber > 1) {
            eventsItemsList?.removeAt(eventsItemsList?.size!! - 1)
            eventsListAdapter.notifyDataSetChanged()
        }
        if (eventsItemsList == null) {
            eventsItemsList = eventsItems
        } else {
            eventsItemsList?.addAll(eventsItems!!)
        }
        eventsListAdapter.notifyDataSetChanged()
        pageNumber++
    }

    override fun onEventsLoadedFailed() {
        loadingProgressBar.visibility = View.GONE
        eventsItemsRecyclerView.visibility = View.GONE
        errorMessage.visibility = View.VISIBLE
    }

}