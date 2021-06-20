package com.suhaili.gameinfoapp.di

import com.suhaili.gameinfoapp.core.domain.usecase.GameUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavouriteDependencies {

    fun useCaseGame(): GameUseCase

}