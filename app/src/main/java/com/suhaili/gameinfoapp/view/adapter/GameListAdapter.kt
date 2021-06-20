package com.suhaili.gameinfoapp.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.suhaili.gameinfoapp.R
import com.suhaili.gameinfoapp.core.databinding.ListGameBinding
import com.suhaili.gameinfoapp.core.resource.local.model.GameEntity
import com.suhaili.gameinfoapp.core.util.GameListCallBack

class GameListAdapter(private val callback: GameListCallBack) :
    RecyclerView.Adapter<GameListAdapter.ItemTarget>() {


    private val gameList = ArrayList<GameEntity>()

    fun setList(value: List<GameEntity>) {
        if (gameList.isEmpty()) {
            gameList.addAll(value)
            notifyDataSetChanged()
        } else {
            gameList.clear()
            gameList.addAll(value)
            notifyDataSetChanged()
        }
    }


    inner class ItemTarget(private val binding: ListGameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: GameEntity) {

            Glide.with(itemView.context)
                .load(value.backgroundImage)
                .apply(RequestOptions().transform(RoundedCorners(10), CenterCrop()))
                .placeholder(R.drawable.ic_baseline_refresh_24)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(binding.picGame)

            Glide.with(itemView.context)
                .load(pictureEsrb(value.esrb_rating))
                .apply(RequestOptions().override(80, 80))
                .placeholder(R.drawable.ic_baseline_refresh_24)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(binding.ratingImage)

            Log.d("TAGPlatform", "${value.platform}")

            binding.titleGame.text = value.game_name
            binding.genre.text = value.genre_game

            binding.cvItem.setOnClickListener {
                callback.itemCallBack(value)
            }
        }

        private fun pictureEsrb(string: String?): Int {
            when (string) {
                "Adult Only" -> {
                    return R.drawable.adult
                }
                "Teen" -> {
                    return R.drawable.teen
                }
                "Everyone" -> {
                    return R.drawable.everyone
                }
                "Everyone 10+" -> {
                    return R.drawable.everyone10plus
                }
                "Mature" -> {
                    return R.drawable.mature
                }
                else -> {
                    return R.drawable.ratingpending
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTarget {
        return ItemTarget(
            ListGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemTarget, position: Int) {
        holder.bind(gameList[position])
    }

    override fun getItemCount(): Int = gameList.size


}