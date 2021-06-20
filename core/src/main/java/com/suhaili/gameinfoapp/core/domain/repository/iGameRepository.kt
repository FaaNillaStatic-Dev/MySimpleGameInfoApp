package com.suhaili.gameinfoapp.core.domain.repository

import com.suhaili.gameinfoapp.core.resource.local.model.GameEntity
import com.suhaili.gameinfoapp.core.util.vo.Resource
import kotlinx.coroutines.flow.Flow

interface iGameRepository {

    fun getAllGame(): Flow<Resource<List<GameEntity>>>
    fun getAllFavoriteGame(): Flow<Resource<List<GameEntity>>>
    fun setFavourite(value: GameEntity)
    fun searchGame(value: String): Flow<Resource<List<GameEntity>>>

}