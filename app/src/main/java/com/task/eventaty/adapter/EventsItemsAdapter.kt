package com.task.eventaty.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.task.eventaty.R
import com.task.eventaty.model.SubEventTypeModel
import kotlinx.android.synthetic.main.events_items_list.view.*

/**
 * @author Mohamed Saleh on 6/7/2018.
 */
class EventsItemsAdapter(val eventsItems: ArrayList<SubEventTypeModel>?,
                         val context: Context) : RecyclerView.Adapter<EventsItemsAdapter.CustomViewHolder>() {

    private val MESSAGE_TYPE = 1
    private val LOADING_TYPE = 2


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): EventsItemsAdapter.CustomViewHolder{
        return if (viewType == MESSAGE_TYPE)
        CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.events_items_list, parent, false))
        else
            CustomViewHolder(LayoutInflater.from(context).
                    inflate(R.layout.load_more_item, parent, false))
    }

    override fun getItemCount(): Int {
        if (eventsItems != null) {
            return eventsItems.size
        } else {
            return 0
        }
    }

    override fun onBindViewHolder(holder: EventsItemsAdapter.CustomViewHolder?, position: Int) {
        if (eventsItems?.get(position)?.eventTypeInList == SubEventTypeModel.Companion.EVENT_TYPE_IN_LIST.MESSAGE) {
            holder?.bindEventItem(eventsItems?.get(position))
        }
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindEventItem(eventItem: SubEventTypeModel?) {
            Picasso.with(itemView.context).load(eventItem?.coverImageUrl).into(itemView.eventItemImage)
            itemView.eventItemName.text = eventItem?.name
            itemView.eventItemDate.text = eventItem?.startDate
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (eventsItems?.get(position)?.eventTypeInList ==
                SubEventTypeModel.Companion.EVENT_TYPE_IN_LIST.MESSAGE) MESSAGE_TYPE
        else
            LOADING_TYPE
    }
}