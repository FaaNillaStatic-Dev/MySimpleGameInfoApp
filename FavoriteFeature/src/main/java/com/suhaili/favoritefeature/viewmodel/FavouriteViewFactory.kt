package com.suhaili.favoritefeature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suhaili.gameinfoapp.core.domain.usecase.GameUseCase
import javax.inject.Inject

class FavouriteViewFactory @Inject constructor(private val userepo: GameUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavouriteViewModel::class.java) -> {
                FavouriteViewModel(userepo) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

}