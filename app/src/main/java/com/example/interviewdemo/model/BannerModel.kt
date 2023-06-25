package com.example.interviewdemo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BannerModel(val imageRes: Int, val textTitle: String, val textDestiption: String) :
    Parcelable
