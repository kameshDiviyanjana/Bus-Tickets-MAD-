package com.example.busticket

import android.os.Parcel
import android.os.Parcelable

data class BusHallt(val halltID : String?=null,val halltname : String?=null) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(halltID)
        parcel.writeString(halltname)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BusHallt> {
        override fun createFromParcel(parcel: Parcel): BusHallt {
            return BusHallt(parcel)
        }

        override fun newArray(size: Int): Array<BusHallt?> {
            return arrayOfNulls(size)
        }
    }
}
