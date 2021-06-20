package com.suhaili.gameinfoapp.modelview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.suhaili.gameinfoapp.core.domain.usecase.GameUseCase
import com.suhaili.gameinfoapp.core.resource.local.model.GameEntity
import com.suhaili.gameinfoapp.core.util.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeModelView @Inject constructor(private val useCase: GameUseCase) : ViewModel() {
    val getAllGame = useCase.getAllGame().asLiveData()
    fun setFavourite(value: GameEntity) = useCase.setFavourite(value)


    fun searchGame(string: String): LiveData<Resource<List<GameEntity>>> =
        useCase.searchGame(string).asLiveData()
}