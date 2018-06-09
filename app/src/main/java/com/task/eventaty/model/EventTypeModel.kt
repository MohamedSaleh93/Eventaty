package com.task.eventaty.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * @author Mohamed Saleh on 6/7/2018.
 */
class EventTypeModel() : Parcelable {
    @SerializedName("name")
    lateinit var eventName: String
    @SerializedName("id")
    lateinit var eventId: String

    constructor(parcel: Parcel) : this() {
        eventName = parcel.readString()
        eventId = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(eventName)
        parcel.writeString(eventId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventTypeModel> {
        override fun createFromParcel(parcel: Parcel): EventTypeModel {
            return EventTypeModel(parcel)
        }

        override fun newArray(size: Int): Array<EventTypeModel?> {
            return arrayOfNulls(size)
        }
    }
}