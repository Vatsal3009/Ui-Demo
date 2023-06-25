package com.example.interviewdemo.repository

import com.example.interviewdemo.R
import com.example.interviewdemo.model.BannerModel


object DataRepository {

    val age = ArrayList<Int>().apply {
        add(1)
        add(2)
        add(3)
        add(4)
        add(5)
        add(6)
    add(1)
        add(2)
        add(3)
        add(4)
        add(5)
        add(6)
    add(1)
        add(2)
        add(3)
        add(4)
        add(5)
        add(6)
        add(1)
        add(2)
        add(3)
        add(4)
        add(5)
        add(6)
    }

     val banners = ArrayList<BannerModel>().apply {
         add(
             com.example.interviewdemo.model.BannerModel(
                 com.example.interviewdemo.R.drawable.ic_location_a,
                 "London",
                 "NEW OSOGBO"
             )
         )
         add(
             BannerModel(
                 R.drawable.ic_location_b,
                 "Canada",
                 "NEW OSOGBO"
             )
         )
         add(
             BannerModel(
                 R.drawable.ic_location_c,
                 "USA",
                 "NEW OSOGBO"
             )
         )
         add(
             BannerModel(
                 R.drawable.ic_location_d,
                 "India",
                 "NEW OSOGBO"
             )
         )
         add(
             BannerModel(
                 R.drawable.ic_location_e,
                 "Germany",
                 "NEW OSOGBO"
             )
         )
         add(
             com.example.interviewdemo.model.BannerModel(
                 com.example.interviewdemo.R.drawable.ic_location_a,
                 "London",
                 "NEW OSOGBO"
             )
         )
     }


}