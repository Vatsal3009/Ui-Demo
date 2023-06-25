package com.example.interviewdemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.interviewdemo.adapters.BannerAdapter
import com.example.interviewdemo.adapters.SelectAgeAdapter
import com.example.interviewdemo.databinding.ActivityDetailsBinding
import com.example.interviewdemo.model.BannerModel
import com.example.interviewdemo.repository.DataRepository
import com.example.interviewdemo.utils.KEY_BANNER_DATA
import com.google.android.material.bottomsheet.BottomSheetBehavior

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    private var currentPage = 0
    private var totalPage = 0
    private var selectAgeAdapter: SelectAgeAdapter? = null


    private lateinit var sheetOneBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var sheetTwoBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var sheetThreeBehavior: BottomSheetBehavior<ConstraintLayout>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bannerData = intent.extras?.get(KEY_BANNER_DATA) as BannerModel
        setData(bannerData)
        sheetOneBehavior = BottomSheetBehavior.from(binding.dialogBottom.bottomSheet)
        sheetTwoBehavior = BottomSheetBehavior.from(binding.dialogBottom2.bottomSheet)
        sheetThreeBehavior = BottomSheetBehavior.from(binding.dialogBottom3.bottomSheet)
        sheetTwoBehavior.setPeekHeight(dpToPx(296f), true)
        sheetThreeBehavior.setPeekHeight(dpToPx(396f), true)
        sheetOneBehavior.isDraggable = false

        sheetTwoBehavior.isDraggable = false
        sheetTwoBehavior.isHideable = false

        sheetThreeBehavior.isDraggable = false
        sheetThreeBehavior.isHideable = false

        //first click
        binding.btnScheduleTrip.setOnClickListener {
            expandCollapseSelectDateSheet()
        }

        //after first click
        binding.dialogBottom.locationLayout.root.setOnClickListener {
            binding.dialogBottom3.root.visibility = View.GONE
            sheetTwoBehavior.setPeekHeight(dpToPx(100f), true)

            if (sheetTwoBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                sheetTwoBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                binding.dialogBottom2.layoutSeats.root.visibility = View.VISIBLE
                binding.dialogBottom2.layoutAdults.visibility = View.GONE

            }
            if (sheetThreeBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                sheetThreeBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }

        }

        binding.dialogBottom2.layoutAdults.setOnClickListener {
            binding.dialogBottom3.root.visibility = View.VISIBLE
            binding.dialogBottom3.layoutPayNow.root.visibility = View.VISIBLE

            if (sheetThreeBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                sheetThreeBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }

        }

//selectSeat click
        binding.dialogBottom2.layoutSeats.root.setOnClickListener {
            expandSelectSeatsDataSheet()
        }

        //payNowClick
        binding.dialogBottom3.layoutPayNow.root.setOnClickListener {
            expandPayNowSheet()
        }

        binding.dialogBottom3.btnDone.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }


    }

    private val pageCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            selectAgeAdapter?.setSelectedPosition(position + 1)

        }
    }


    private fun expandSelectSeatsDataSheet() {

        if (sheetTwoBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            sheetTwoBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            binding.dialogBottom2.layoutSeats.root.visibility = View.GONE
            binding.dialogBottom2.layoutAdults.visibility = View.VISIBLE
            binding.dialogBottom3.root.visibility = View.VISIBLE
            binding.dialogBottom3.layoutPayNow.root.visibility = View.VISIBLE

        } else {
            sheetTwoBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            binding.dialogBottom2.layoutSeats.root.visibility = View.VISIBLE
            binding.dialogBottom2.layoutAdults.visibility = View.GONE


        }
    }

    private fun expandPayNowSheet() {

        Log.e("==>dada", "expandPayNowSheet:${sheetTwoBehavior.state} ", )
        if (sheetThreeBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            sheetThreeBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            binding.dialogBottom3.layoutPayNow.root.visibility = View.GONE
            binding.dialogBottom3.mainLayout.visibility = View.VISIBLE
        } else {
            sheetTwoBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            binding.dialogBottom3.layoutPayNow.root.visibility = View.VISIBLE
            binding.dialogBottom3.mainLayout.visibility = View.GONE
        }
    }

    private fun expandCollapseSelectDateSheet() {
        if (sheetOneBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            sheetOneBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            binding.dialogBottom2.root.visibility = View.VISIBLE
        } else {
            sheetOneBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun setData(bannerData: BannerModel) {
        binding.tvTitle.text = bannerData.textTitle
        binding.icDetail.setImageResource(bannerData.imageRes)

        selectAgeAdapter = SelectAgeAdapter(DataRepository.age)
        selectAgeAdapter?.setSelectedPosition(0)
        binding.dialogBottom2.viewPager.apply {
            adapter = selectAgeAdapter
            clipToPadding = false
            clipChildren = false
            if (DataRepository.banners.size > 0) {
                offscreenPageLimit = DataRepository.banners.size
            }
            totalPage = DataRepository.age.size
            setCurrentItem(2, true)
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            registerOnPageChangeCallback(pageCallback)
        }
    }

    fun dpToPx(dp: Float): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }
}