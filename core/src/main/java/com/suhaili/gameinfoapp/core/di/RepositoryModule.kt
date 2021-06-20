package com.suhaili.gameinfoapp.core.di

import com.suhaili.gameinfoapp.core.domain.repository.iGameRepository
import com.suhaili.gameinfoapp.core.resource.GameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [DatabaseModule::class, NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun madeRepository(repo: GameRepository): iGameRepository

}