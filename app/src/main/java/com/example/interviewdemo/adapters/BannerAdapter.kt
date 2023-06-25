package com.example.interviewdemo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewdemo.databinding.ItemBannerBinding
import com.example.interviewdemo.model.BannerModel

class BannerAdapter(
    private val items: ArrayList<BannerModel>,
    private val onItemClick: (BannerModel) -> Unit
) : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(
        private val binding: ItemBannerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(banner: BannerModel) {
            binding.tvTitle.text = banner.textTitle
            binding.tvDescription.text = banner.textDestiption

            binding.icBanner.setImageResource(banner.imageRes)
            binding.mainCard.setOnClickListener {
                onItemClick(banner)
            }

        }
    }
}