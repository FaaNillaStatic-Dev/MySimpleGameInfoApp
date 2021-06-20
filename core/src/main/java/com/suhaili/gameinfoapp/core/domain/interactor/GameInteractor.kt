package com.suhaili.gameinfoapp.core.domain.interactor

import com.suhaili.gameinfoapp.core.domain.repository.iGameRepository
import com.suhaili.gameinfoapp.core.domain.usecase.GameUseCase
import com.suhaili.gameinfoapp.core.resource.local.model.GameEntity
import com.suhaili.gameinfoapp.core.util.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameInteractor @Inject constructor(private val repository: iGameRepository) : GameUseCase {

    override fun getAllGame(): Flow<Resource<List<GameEntity>>> = repository.getAllGame()

    override fun getAllFavoriteGame(): Flow<Resource<List<GameEntity>>> =
        repository.getAllFavoriteGame()

    override fun setFavourite(value: GameEntity) = repository.setFavourite(value)

    override fun searchGame(value: String): Flow<Resource<List<GameEntity>>> =
        repository.searchGame(value)
}