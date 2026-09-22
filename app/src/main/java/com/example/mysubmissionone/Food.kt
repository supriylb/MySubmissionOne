package com.example.mysubmissionone

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    var image: Int = 0,
    var name: String = "name",
    var location: String = "location",
    var description: String = "description"
) : Parcelable
