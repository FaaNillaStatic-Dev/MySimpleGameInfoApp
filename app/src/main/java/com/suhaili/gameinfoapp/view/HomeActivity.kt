@file:Suppress(
    "LiftReturnOrAssignment", "LiftReturnOrAssignment", "LiftReturnOrAssignment",
    "LiftReturnOrAssignment"
)

package com.suhaili.gameinfoapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.snackbar.Snackbar
import com.suhaili.gameinfoapp.R
import com.suhaili.gameinfoapp.core.resource.local.model.GameEntity
import com.suhaili.gameinfoapp.core.util.GameListCallBack
import com.suhaili.gameinfoapp.core.util.vo.Status
import com.suhaili.gameinfoapp.databinding.ActivityMainBinding
import com.suhaili.gameinfoapp.modelview.HomeModelView
import com.suhaili.gameinfoapp.view.adapter.GameListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), GameListCallBack {
    private lateinit var binding: ActivityMainBinding

    private val mainView: HomeModelView by viewModels()

    private lateinit var adapterGame: GameListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "SeeGame Home!"

        adapterGame = GameListAdapter(this)
        binding.rvHome.layoutManager = LinearLayoutManager(this)
        binding.rvHome.setHasFixedSize(true)
        binding.rvHome.adapter = adapterGame
        loadAll()

        binding.searchList.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.isNotEmpty()!!) {
                    loadSearch(query)
                    return false
                } else {
                    return false
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isNotEmpty()!!) {
                    loadSearch(newText)
                    return false
                } else {
                    loadAll()
                    return false
                }
            }

        })


    }

    internal fun loadAll() {
        mainView.getAllGame.observe(this, { (status, data) ->
            when (status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    binding.rvHome.visibility = View.VISIBLE
                    adapterGame.setList(data ?: return@observe)
                }
                Status.LOADING -> {
                    binding.progress.setAnimation(R.raw.loading)
                    binding.progress.repeatCount = LottieDrawable.INFINITE
                    binding.progress.playAnimation()
                    binding.progress.visibility = View.VISIBLE
                    binding.rvHome.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    binding.rvHome.visibility = View.GONE
                    Toast.makeText(this, "Error Get Data!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    internal fun loadSearch(text: String) {
        mainView.searchGame(text).observe(this, { (status, data) ->
            when (status) {
                Status.SUCCESS -> {
                    if(data?.isEmpty()!!){
                        binding.progress.setAnimation(R.raw.empty)
                        binding.progress.repeatCount = LottieDrawable.INFINITE
                        binding.progress.playAnimation()
                        binding.progress.visibility = View.VISIBLE
                        binding.rvHome.visibility = View.GONE
                        Snackbar.make(binding.root,"Nothing Found What You Find!",Snackbar.LENGTH_SHORT).show()
                    }else{
                        binding.progress.visibility = View.GONE
                        binding.rvHome.visibility = View.VISIBLE
                        adapterGame.setList(data)
                    }

                }
                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.rvHome.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.rvHome.visibility = View.GONE
                    Toast.makeText(this, "Error Get Data!", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    override fun itemCallBack(value: GameEntity) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.KEY_DETAIL, value)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favoriteMenu -> {
                try {
                    val uri = Uri.parse("gameinfoapp://favourite")
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                } catch (e: Exception) {
                    Toast.makeText(this, "Module Not Found!", Toast.LENGTH_SHORT).show()
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }
}