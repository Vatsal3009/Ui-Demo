package com.example.interviewdemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract.Data
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.interviewdemo.R
import com.example.interviewdemo.adapters.BannerAdapter
import com.example.interviewdemo.adapters.GridAdapter
import com.example.interviewdemo.databinding.ActivityMainBinding
import com.example.interviewdemo.model.BannerModel
import com.example.interviewdemo.repository.DataRepository
import com.example.interviewdemo.utils.KEY_BANNER_DATA
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.delay
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private var bannerAdapter: BannerAdapter? = null
    private var autoScrollTime = 2000L
    private var currentPage = 0
    private var totalPage = 0
    private val handler = Handler(Looper.getMainLooper())

    private lateinit var sheetOneBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var sheetTwoBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var sheetThreeBehavior: BottomSheetBehavior<ConstraintLayout>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewPager()
        setUpGridView()

        /*  binding.dialogBottom2.bottomSheet.setOnClickListener {
  expandCollapseSecoundSheet()
          }*/

    }


    private val update = object : Runnable {
        override fun run() {
            justTry {
                if (currentPage == totalPage) {
                    currentPage = 0
                }
                binding.viewPager.setCurrentItem(currentPage++, true)
                handler.postDelayed(this, autoScrollTime)
            }
        }
    }
    private val pageCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            currentPage = position
        }
    }

    private fun setViewPager() {

        bannerAdapter = BannerAdapter(DataRepository.banners, ::onItemCLick)
        binding.viewPager.adapter = bannerAdapter

        binding.viewPager.apply {
            adapter = bannerAdapter
            clipToPadding = false
            clipChildren = false
            if (DataRepository.banners.size > 0) {
                offscreenPageLimit = DataRepository.banners.size
            }
            totalPage = DataRepository.banners.size
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            registerOnPageChangeCallback(pageCallback)
        }
        handler.postDelayed(update, autoScrollTime)
    }


    private fun setUpGridView() {

        binding.gridView.adapter = GridAdapter(DataRepository.banners, ::onItemCLick)
    }

    private fun onItemCLick(bannerModel: BannerModel) {
        // setUpSheetView()
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(KEY_BANNER_DATA, bannerModel)
        startActivity(intent)

    }





    private fun expandCollapseSecoundSheet() {
        if (sheetTwoBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            sheetTwoBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            sheetTwoBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        }

    }





    inline fun <T> justTry(tryBlock: () -> T) = try {
        tryBlock()
    } catch (e: Throwable) {
        e.printStackTrace()
    }
}

