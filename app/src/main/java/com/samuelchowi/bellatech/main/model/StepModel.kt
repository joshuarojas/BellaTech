package com.samuelchowi.bellatech.main.model

import android.os.Parcel
import android.os.Parcelable

class StepModel(
        var title: String? = null,
        var body: String? = null) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StepModel> {
        override fun createFromParcel(parcel: Parcel): StepModel {
            return StepModel(parcel)
        }

        override fun newArray(size: Int): Array<StepModel?> {
            return arrayOfNulls(size)
        }
    }
}