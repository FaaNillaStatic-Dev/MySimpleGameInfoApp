package com.suhaili.gameinfoapp.core.resource

import com.suhaili.gameinfoapp.core.domain.repository.iGameRepository
import com.suhaili.gameinfoapp.core.resource.local.LocalServices
import com.suhaili.gameinfoapp.core.resource.local.model.GameEntity
import com.suhaili.gameinfoapp.core.resource.remote.RemoteServices
import com.suhaili.gameinfoapp.core.resource.remote.model.ResultsItem
import com.suhaili.gameinfoapp.core.util.AppExecutor
import com.suhaili.gameinfoapp.core.util.vo.ApiResponse
import com.suhaili.gameinfoapp.core.util.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val local: LocalServices,
    private val remote: RemoteServices,
    private val execute: AppExecutor
) : iGameRepository {


    override fun getAllGame(): Flow<Resource<List<GameEntity>>> {
        return object : NetworkBoundResource<List<GameEntity>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<GameEntity>> = local.getAllGame()

            override fun shouldFetch(data: List<GameEntity>): Boolean =
                data == null || data.isEmpty()


            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remote.getGamedata()


            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val arrTemp = ArrayList<GameEntity>()
                for (i in 0 until data.size) {
                    val dataResp = data[i]
                    val genre = StringBuilder()
                    val tag = StringBuilder()
                    val store = StringBuilder()
                    val platform = StringBuilder()
                    val screenshotsItem = StringBuilder()
                    for (i in 0 until (dataResp.genres?.size ?: return)) {
                        val data = dataResp.genres[i]
                        if (i == dataResp.genres.size - 1) {
                            genre.append(data.name)
                        } else {
                            genre.append(data.name + ",")
                        }
                    }
                    for (i in 0 until (dataResp.shortScreenshots?.size ?: return)) {
                        val data = dataResp.shortScreenshots[i]
                        if (i == dataResp.shortScreenshots.size - 1) {
                            screenshotsItem.append(data.image)
                        } else {
                            screenshotsItem.append(data.image + "  ")
                        }
                    }

                    for (i in 0 until (dataResp.tags?.size ?: return)) {
                        val data = dataResp.tags[i]
                        if (i == dataResp.tags.size - 1) {
                            tag.append(data.name)
                        } else {
                            tag.append(data.name + ",")
                        }
                    }

                    for (i in 0 until (dataResp.parentPlatforms?.size ?: return)) {
                        val data = dataResp.parentPlatforms[i]
                        if (i == dataResp.parentPlatforms.size - 1) {
                            platform.append(data.platform?.name)
                        } else {
                            platform.append(data.platform?.name + ",")
                        }
                    }

                    for (i in 0 until (dataResp.stores?.size ?: return)) {
                        val data = dataResp.stores[i]
                        if (i == dataResp.stores.size - 1) {
                            store.append(data.store?.name)
                        } else {
                            store.append(data.store?.name + ",")
                        }
                    }

                    val dataGame = GameEntity(
                        dataResp.id,
                        dataResp.name,
                        dataResp.released,
                        dataResp.backgroundImage,
                        dataResp.rating,
                        dataResp.ratingTop,
                        dataResp.updated,
                        dataResp.esrbRating?.name,
                        genre.toString(),
                        screenshotsItem.toString(),
                        tag.toString(),
                        store.toString(),
                        platform.toString(),
                        0
                    )

                    arrTemp.add(dataGame)
                }
                local.insertAllGame(arrTemp)
            }

        }.asFlow()
    }

    override fun getAllFavoriteGame(): Flow<Resource<List<GameEntity>>> {
        return object : NetworkBoundResource<List<GameEntity>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<GameEntity>> = local.getAllFavourite()

            override fun shouldFetch(data: List<GameEntity>): Boolean =
                false


            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remote.getGamedata()


            override suspend fun saveCallResult(data: List<ResultsItem>) {}

        }.asFlow()
    }

    override fun setFavourite(value: GameEntity) {
        execute.DiskIO().execute {
            local.updateGame(value)
        }
    }

    override fun searchGame(value: String): Flow<Resource<List<GameEntity>>> {
        return object : NetworkBoundResource<List<GameEntity>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<GameEntity>> {
                return local.searchGame(value)
            }

            override fun shouldFetch(data: List<GameEntity>): Boolean {
                return false
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remote.getGamedata()

            override suspend fun saveCallResult(data: List<ResultsItem>) {
            }

        }.asFlow()
    }


}