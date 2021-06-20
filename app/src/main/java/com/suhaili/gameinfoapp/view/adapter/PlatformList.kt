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


class PlatformList : RecyclerView.Adapter<PlatformList.ItemTarget>() {
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
                "PC" -> R.drawable.windows
                "PlayStation" -> R.drawable.playstationlogotype
                "Xbox" -> R.drawable.xbox
                "iOS" -> R.drawable.apple
                "Android" -> R.drawable.android
                "Apple Macintosh" -> R.drawable.apple
                "Linux" -> R.drawable.linux
                "Nintendo" -> R.drawable.nintendo
                "Web" -> R.drawable.global
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