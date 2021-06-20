package com.suhaili.gameinfoapp.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.suhaili.gameinfoapp.R
import com.suhaili.gameinfoapp.core.resource.local.model.GameEntity
import com.suhaili.gameinfoapp.databinding.ActivityDetailBinding
import com.suhaili.gameinfoapp.modelview.HomeModelView
import com.suhaili.gameinfoapp.view.adapter.PlatformList
import com.suhaili.gameinfoapp.view.adapter.ScreenShootList
import com.suhaili.gameinfoapp.view.adapter.StoreList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val KEY_DETAIL = "detail"
    }

    private val mainView: HomeModelView by viewModels()
    private lateinit var storeAdapter: StoreList
    private lateinit var ssAdapter: ScreenShootList
    private lateinit var platformAdapter: PlatformList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.extras
        val value = data?.getParcelable<GameEntity>(KEY_DETAIL) as GameEntity
        supportActionBar?.title = value.game_name + " Information"

        loadImages(value.backgroundImage, binding.picDetail)
        loadImages(value.esrb_rating, binding.imageRating)
        loadText(value)
        loadPlatform(value.platform?.split(",")?.toTypedArray() ?: return)
        loadScreenshot(value.screenShoot?.split("  ")?.toTypedArray() ?: return)
        loadStore(value.store?.split(",")?.toTypedArray() ?: return)
        if (value.isFavorite == 0) {
            binding.favButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        } else {
            binding.favButton.setImageResource(R.drawable.ic_baseline_favorite_24)
        }

        binding.favButton.setOnClickListener {
            if (value.isFavorite == 0) {
                value.isFavorite = 1
                mainView.setFavourite(value)
                binding.favButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                Snackbar.make(
                    binding.root,
                    "${value.game_name} Add To Favorite List!",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                value.isFavorite = 0
                mainView.setFavourite(value)
                binding.favButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                Snackbar.make(
                    binding.root,
                    "${value.game_name} Delete From Favorite List!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun loadImages(image: String?, location: ImageView) {
        when (location) {
            binding.picDetail -> {
                Glide.with(this)
                    .load(image)
                    .apply(RequestOptions().transform(CenterCrop()))
                    .placeholder(R.drawable.ic_baseline_refresh_24)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(location)
            }
            binding.imageRating -> {
                Glide.with(this)
                    .load(pictureEsrb(image))
                    .placeholder(R.drawable.ic_baseline_refresh_24)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(location)
            }

        }

    }

    private fun loadText(value: GameEntity) {
        binding.titleGame.text = value.game_name
        binding.genre.text = value.genre_game
        binding.release.text = value.release_game
        binding.rate.text = value.rating?.toString()
        binding.topRate.text = value.rating_top?.toString()
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


    private fun loadPlatform(platform: Array<String>) {
        val stringList = ArrayList<String>()
        for (i in platform.indices) {
            stringList.add(platform[i])
        }
        binding.rvPlatrform.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPlatrform.setHasFixedSize(true)
        platformAdapter = PlatformList()
        binding.rvPlatrform.adapter = platformAdapter
        platformAdapter.setList(stringList)
    }

    private fun loadStore(platform: Array<String>) {

        val stringList = ArrayList<String>()
        for (i in platform.indices) {
            stringList.add(platform[i])
        }
        binding.rvStore.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPlatrform.setHasFixedSize(true)
        storeAdapter = StoreList()
        binding.rvStore.adapter = storeAdapter
        storeAdapter.setList(stringList)
    }

    private fun loadScreenshot(platform: Array<String>) {

        val stringList = ArrayList<String>()
        for (i in platform.indices) {
            stringList.add(platform[i])
        }

        binding.rvScreenshoot.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvScreenshoot.setHasFixedSize(true)
        ssAdapter = ScreenShootList()
        binding.rvScreenshoot.adapter = ssAdapter
        ssAdapter.setList(stringList)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            else -> return false
        }


    }


}

