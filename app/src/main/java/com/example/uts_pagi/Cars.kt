package com.example.uts_pagi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cars (
    val photo : String,
    val name : String,
    val kind : String,
    val price : Int,
    val seats : Int,
    val baggage_amount: Int,
    val description: String
) : Parcelable