package com.example.bottomSheetDemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.bottomSheetDemo.model.BannerModel
import com.example.interviewdemo.R

class GridAdapter(
    private val items: ArrayList<BannerModel>,
    private val onItemClick: (BannerModel) -> Unit
)  : BaseAdapter() {


    override fun getCount() = items.size

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_grid, parent, false)
        val imageView = view.findViewById<ImageView>(R.id.icBannerGrid)
        val cardView = view.findViewById<CardView>(R.id.mainCardGrid)
        val textView = view.findViewById<TextView>(R.id.tvTitleGrid)

        val item = getItem(position)
        imageView.setImageResource(item.imageRes)
        textView.text = item.textTitle

        cardView.setOnClickListener {
            onItemClick(item)
        }

        return view
    }
}