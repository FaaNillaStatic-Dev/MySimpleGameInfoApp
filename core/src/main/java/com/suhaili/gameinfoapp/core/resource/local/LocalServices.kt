package com.suhaili.gameinfoapp.core.resource.local

import com.suhaili.gameinfoapp.core.resource.local.model.GameEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalServices @Inject constructor(private val Dao: GameDAO) {


    fun getAllGame(): Flow<List<GameEntity>> = Dao.getAllGame()

    fun getAllFavourite(): Flow<List<GameEntity>> = Dao.getAllFavorite()

    fun searchGame(value: String): Flow<List<GameEntity>> = Dao.searchGame(value)

    suspend fun insertAllGame(value: List<GameEntity>) = Dao.insertAllResponse(value)

    fun updateGame(value: GameEntity) = Dao.updateItem(value)


}