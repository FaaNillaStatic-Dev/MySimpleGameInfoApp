package com.suhaili.gameinfoapp.core.resource.remote


import com.suhaili.gameinfoapp.core.BuildConfig
import com.suhaili.gameinfoapp.core.resource.remote.model.GameResponse
import retrofit2.http.GET

interface APIServices {
    @GET("games?key=${BuildConfig.API_KEY}&page=1&page_size=40")
    suspend fun GetAllGameData(): GameResponse
}