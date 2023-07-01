package com.example.bottomSheetDemo.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewdemo.databinding.ItemSelectAgeBinding


class SelectAgeAdapter(
    private val items: ArrayList<Int>,
) : RecyclerView.Adapter<SelectAgeAdapter.ViewHolder>() {
    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSelectAgeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(items[position], items[position] == selectedPosition)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(
        private val binding: ItemSelectAgeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(banner: Int, isSelected: Boolean) {

            Log.e("==>selected", "bindData: $isSelected", )
            binding.textView.text = banner.toString()
            if(isSelected == true){
                binding.textView.textSize = 180f
                binding.textView.setTextColor(Color.WHITE)
            }else{
                binding.textView.textSize = 88f
                binding.textView.setTextColor(Color.DKGRAY)
            }

        }
    }

    fun setSelectedPosition(position: Int) {
      /*  selectedPosition = position
        notifyDataSetChanged()*/
    }
}