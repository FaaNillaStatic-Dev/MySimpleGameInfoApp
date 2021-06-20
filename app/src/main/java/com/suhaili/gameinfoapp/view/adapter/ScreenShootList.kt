package com.suhaili.gameinfoapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.suhaili.gameinfoapp.R
import com.suhaili.gameinfoapp.core.databinding.ItemScreenshootBinding


class ScreenShootList : RecyclerView.Adapter<ScreenShootList.ItemTarget>() {
    inner class ItemTarget(private val binding: ItemScreenshootBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: String) {
            Glide.with(itemView.context)
                .load(value)
                .placeholder(R.drawable.ic_baseline_refresh_24)
                .error(R.drawable.ic_baseline_error_outline_24)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(10)))
                .into(binding.picScreen)
        }
    }

    private val data = ArrayList<String>()

    fun setList(value: ArrayList<String>) {
        if (data.isEmpty()) {
            data.addAll(value)
            notifyDataSetChanged()
        } else {
            data.clear()
            data.addAll(value)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTarget = ItemTarget(
        ItemScreenshootBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun onBindViewHolder(holder: ItemTarget, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}