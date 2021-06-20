package com.suhaili.favoritefeature

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.snackbar.Snackbar
import com.suhaili.favoritefeature.databinding.ActivityFavouriteBinding
import com.suhaili.favoritefeature.di.DaggerFavouriteComponent
import com.suhaili.favoritefeature.viewmodel.FavouriteViewFactory
import com.suhaili.favoritefeature.viewmodel.FavouriteViewModel
import com.suhaili.gameinfoapp.R
import com.suhaili.gameinfoapp.core.resource.local.model.GameEntity
import com.suhaili.gameinfoapp.core.util.GameListCallBack
import com.suhaili.gameinfoapp.core.util.vo.Status
import com.suhaili.gameinfoapp.di.FavouriteDependencies
import com.suhaili.gameinfoapp.view.DetailActivity
import com.suhaili.gameinfoapp.view.adapter.GameListAdapter
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class FavouriteActivity : AppCompatActivity(), GameListCallBack {
    private lateinit var binding: ActivityFavouriteBinding

    @Inject
    lateinit var factory: FavouriteViewFactory


    private val mainView: FavouriteViewModel by viewModels {
        factory
    }

    private lateinit var adapterList: GameListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavouriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavouriteDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favourite Game List!"
        mainView.loadFavourite.observe(this, { (status, data) ->
            when (status) {
                Status.SUCCESS -> {
                    if (data?.isEmpty() ?: return@observe) {
                        binding.loadingprogress.setAnimation(R.raw.empty)
                        binding.loadingprogress.repeatCount = LottieDrawable.INFINITE
                        binding.loadingprogress.playAnimation()
                        binding.loadingprogress.visibility = View.VISIBLE
                        binding.cardRv.visibility = View.GONE
                        Snackbar.make(binding.root,"Are You Add Game To Favorite?", Snackbar.LENGTH_SHORT).show()
                    } else {
                        binding.loadingprogress.visibility = View.GONE
                        binding.cardRv.visibility = View.VISIBLE
                        loadList(data)
                    }

                }
                Status.LOADING -> {
                    binding.loadingprogress.setAnimation(R.raw.loading)
                    binding.loadingprogress.repeatCount = LottieDrawable.INFINITE
                    binding.loadingprogress.playAnimation()
                    binding.loadingprogress.visibility = View.VISIBLE
                    binding.cardRv.visibility = View.GONE
                }
                Status.ERROR -> {
                    Toast.makeText(this, "Failed GetData!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun loadList(list: List<GameEntity>) {
        adapterList = GameListAdapter(this)
        binding.cardRv.layoutManager = LinearLayoutManager(this)
        binding.cardRv.setHasFixedSize(true)
        binding.cardRv.adapter = adapterList
        adapterList.setList(list)
    }

    override fun itemCallBack(value: GameEntity) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.KEY_DETAIL, value)
        startActivity(intent)
        finish()
    }
}