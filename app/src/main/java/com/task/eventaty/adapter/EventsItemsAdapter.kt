package com.task.eventaty.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.task.eventaty.R
import com.task.eventaty.model.SubEventTypeModel
import kotlinx.android.synthetic.main.events_items_list.view.*

/**
 * @author Mohamed Saleh on 6/7/2018.
 */
class EventsItemsAdapter(val eventsItems: ArrayList<SubEventTypeModel>?,
                         val context: Context) : RecyclerView.Adapter<EventsItemsAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): EventsItemsAdapter.CustomViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.events_items_list, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (eventsItems != null) {
            return eventsItems.size
        } else {
            return 0
        }
    }

    override fun onBindViewHolder(holder: EventsItemsAdapter.CustomViewHolder?, position: Int) {
        holder?.bindEventItem(eventsItems?.get(position))
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindEventItem(eventItem: SubEventTypeModel?) {
         //   Glide.with(itemView.context).load(eventItem?.coverImageUrl).into(itemView.eventItemImage)
            itemView.eventItemName.text = eventItem?.name
            itemView.eventItemDate.text = eventItem?.startDate
        }
    }
}