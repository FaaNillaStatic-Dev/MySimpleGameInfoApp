package com.suhaili.gameinfoapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.suhaili.gameinfoapp.R
import com.suhaili.gameinfoapp.core.databinding.ItemPlatformBinding


class StoreList : RecyclerView.Adapter<StoreList.ItemTarget>() {
    inner class ItemTarget(private val binding: ItemPlatformBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: String) {
            Glide.with(itemView.context)
                .load(checkPlatform(value))
                .placeholder(R.drawable.ic_baseline_refresh_24)
                .error(R.drawable.ic_baseline_error_outline_24)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(10)))
                .into(binding.picPlatform)

            binding.textPlatform.text = value
        }

        private fun checkPlatform(string: String?): Int {
            return when (string) {
                "App Store" -> R.drawable.appstore
                "PlayStation Store" -> R.drawable.psstore
                "Xbox Store" -> R.drawable.xbox_store
                "Steam" -> R.drawable.steam_logo
                "GOG" -> R.drawable.gogstore
                "Xbox 360 Store" -> R.drawable.xbox_store
                "Nintendo Store" -> R.drawable.nintentostore
                "Google Play" -> R.drawable.playstore
                "Epic Games" -> R.drawable.epicgames
                else -> 0
            }
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
        ItemPlatformBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun onBindViewHolder(holder: ItemTarget, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}