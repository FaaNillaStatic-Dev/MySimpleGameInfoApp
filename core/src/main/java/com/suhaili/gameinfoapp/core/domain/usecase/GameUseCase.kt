package com.suhaili.gameinfoapp.core.domain.usecase

import com.suhaili.gameinfoapp.core.resource.local.model.GameEntity
import com.suhaili.gameinfoapp.core.util.vo.Resource
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGame(): Flow<Resource<List<GameEntity>>>
    fun getAllFavoriteGame(): Flow<Resource<List<GameEntity>>>
    fun setFavourite(value: GameEntity)
    fun searchGame(value: String): Flow<Resource<List<GameEntity>>>
}