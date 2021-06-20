package com.suhaili.favoritefeature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.suhaili.gameinfoapp.core.domain.usecase.GameUseCase


class FavouriteViewModel(private val repository: GameUseCase) : ViewModel() {
    val loadFavourite = repository.getAllFavoriteGame().asLiveData()
}