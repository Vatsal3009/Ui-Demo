package com.example.bottomSheetDemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.bottomSheetDemo.adapters.SelectAgeAdapter

import com.example.bottomSheetDemo.model.BannerModel
import com.example.bottomSheetDemo.repository.DataRepository
import com.example.bottomSheetDemo.utils.KEY_BANNER_DATA
import com.example.interviewdemo.databinding.ActivityDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    private var currentPage = 0
    private var totalPage = 0
    private var selectAgeAdapter: SelectAgeAdapter? = null


    private lateinit var sheetOneBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var sheetTwoBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var sheetThreeBehavior: BottomSheetBehavior<LinearLayout>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bannerData = intent.extras?.get(KEY_BANNER_DATA) as BannerModel
        setData(bannerData)

        //Sheet 1
        sheetOneBehavior = BottomSheetBehavior.from(binding.dialogBottom.bottomSheet)
        sheetOneBehavior.isHideable = false
        sheetOneBehavior.peekHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            0f,
            resources.displayMetrics
        ).toInt()
        //Sheet 2 +40
        sheetTwoBehavior = BottomSheetBehavior.from(binding.dialogBottom2.bottomSheet)
        sheetTwoBehavior.isHideable = false
        sheetTwoBehavior.peekHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            290f,
            resources.displayMetrics
        ).toInt()
        //Sheet 3+40
        sheetThreeBehavior = BottomSheetBehavior.from(binding.dialogBottom3.bottomSheet)
        sheetThreeBehavior.isHideable = false
        sheetThreeBehavior.peekHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            390f,
            resources.displayMetrics
        ).toInt()
        


        //first click
        binding.btnScheduleTrip.setOnClickListener {
            expandCollapseSheet1(sheetOneBehavior, sheetTwoBehavior, sheetThreeBehavior)
            manageSheetOne(sheetOneBehavior)
        }

        //after first click
        binding.dialogBottom.locationLayout.root.setOnClickListener {
            expandCollapseSheet1(sheetOneBehavior, sheetTwoBehavior, sheetThreeBehavior)
            manageSheetOne(sheetOneBehavior)

        }

        binding.dialogBottom2.layoutAdults.setOnClickListener {
            expandCollapseSheet2(sheetTwoBehavior,sheetThreeBehavior)
            manageSheetTwo(sheetTwoBehavior)

        }

//selectSeat click
        binding.dialogBottom2.layoutSeats.root.setOnClickListener {
            expandCollapseSheet2(sheetTwoBehavior,sheetThreeBehavior)
            manageSheetTwo(sheetTwoBehavior)
        }

        //payNowClick
        binding.dialogBottom3.layoutPayNow.root.setOnClickListener {
            expandCollapseSheet3(sheetThreeBehavior)
            manageSheetThree(sheetThreeBehavior)
        }

        binding.dialogBottom3.btnDone.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }


        defaultVisibility()
    }

    private fun defaultVisibility() {
        binding.dialogBottom2.root.visibility = View.GONE
        binding.dialogBottom3.root.visibility = View.GONE
        sheetOneBehavior.isDraggable=false
        sheetTwoBehavior.isDraggable=false
        sheetThreeBehavior.isDraggable=false
    }

    private fun manageSheetOne(sheet: BottomSheetBehavior<LinearLayout>) {
        sheet.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, state: Int) {
                print(state)
                when (state) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        binding.dialogBottom2.root.visibility = View.VISIBLE
                    }

                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        defaultVisibility()
                    }

                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
    }

    private fun manageSheetTwo(sheet: BottomSheetBehavior<LinearLayout>) {
        sheet.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, state: Int) {
                print(state)
                when (state) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        binding.dialogBottom3.root.visibility = View.VISIBLE
                    }

                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        binding.dialogBottom3.root.visibility = View.GONE
                    }

                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
    }

    private fun manageSheetThree(sheet: BottomSheetBehavior<LinearLayout>) {
        sheet.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, state: Int) {
                print(state)
                when (state) {
                    BottomSheetBehavior.STATE_EXPANDED -> {

                    }

                    BottomSheetBehavior.STATE_COLLAPSED -> {

                    }

                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
    }

    private fun expandCollapseSheet1(
        selectedBottomSheet: BottomSheetBehavior<LinearLayout>,
        commonSheet1: BottomSheetBehavior<LinearLayout>,
        commonSheet2: BottomSheetBehavior<LinearLayout>
    ) {

        //when user click on first
        if (selectedBottomSheet.state != BottomSheetBehavior.STATE_EXPANDED) {
            selectedBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
            //binding.persistentBtn.text = "Close Bottom Sheet"
        } else {
            if (commonSheet1.state == BottomSheetBehavior.STATE_EXPANDED || commonSheet2.state == BottomSheetBehavior.STATE_EXPANDED){
                if(commonSheet1.state == BottomSheetBehavior.STATE_EXPANDED){
                    commonSheet1.state = BottomSheetBehavior.STATE_COLLAPSED
                }
                if(commonSheet2.state == BottomSheetBehavior.STATE_EXPANDED){
                    commonSheet2.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }
            else{
                selectedBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            // binding.persistentBtn.text = "Show Bottom Sheet"
        }
        /*   if (commonSheet.state != BottomSheetBehavior.STATE_EXPANDED) {
               commonSheet.state = BottomSheetBehavior.STATE_EXPANDED
               //binding.persistentBtn.text = "Close Bottom Sheet"
           } else {
               commonSheet.state = BottomSheetBehavior.STATE_COLLAPSED
               // binding.persistentBtn.text = "Show Bottom Sheet"
           }*/
    }

    private fun expandCollapseSheet2(
        selectedBottomSheet: BottomSheetBehavior<LinearLayout>,
        commonSheet1: BottomSheetBehavior<LinearLayout>
    ) {
        //when user click on first
        if (selectedBottomSheet.state != BottomSheetBehavior.STATE_EXPANDED) {
            selectedBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
            binding.dialogBottom2.layoutSeats.root.visibility = View.GONE
            //binding.persistentBtn.text = "Close Bottom Sheet"
        } else {
            if (commonSheet1.state == BottomSheetBehavior.STATE_EXPANDED ){
                if(commonSheet1.state == BottomSheetBehavior.STATE_EXPANDED){
                    commonSheet1.state = BottomSheetBehavior.STATE_COLLAPSED
                    binding.dialogBottom3.layoutPayNow.root.visibility = View.VISIBLE
                }else{
                    binding.dialogBottom3.layoutPayNow.root.visibility = View.GONE
                }
            }
            else{
                selectedBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
                binding.dialogBottom2.layoutSeats.root.visibility = View.VISIBLE
            }
            // binding.persistentBtn.text = "Show Bottom Sheet"
        }
    }

    private fun expandCollapseSheet3(
        commonSheet: BottomSheetBehavior<LinearLayout>,
    ) {
        //when user click on first
        if (commonSheet.state != BottomSheetBehavior.STATE_EXPANDED) {
            commonSheet.state = BottomSheetBehavior.STATE_EXPANDED
            binding.dialogBottom3.layoutPayNow.root.visibility = View.GONE
            //binding.persistentBtn.text = "Close Bottom Sheet"
        } else {
            commonSheet.state = BottomSheetBehavior.STATE_COLLAPSED
            binding.dialogBottom3.layoutPayNow.root.visibility = View.VISIBLE
            // binding.persistentBtn.text = "Show Bottom Sheet"
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