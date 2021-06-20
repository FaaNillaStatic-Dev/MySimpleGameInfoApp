package com.suhaili.gameinfoapp


import com.suhaili.gameinfoapp.core.domain.interactor.GameInteractor
import com.suhaili.gameinfoapp.core.domain.usecase.GameUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun ViewModel(repo: GameInteractor): GameUseCase

}