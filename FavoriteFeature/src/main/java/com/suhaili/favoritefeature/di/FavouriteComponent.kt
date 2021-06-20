package com.suhaili.favoritefeature.di

import android.content.Context
import com.suhaili.favoritefeature.FavouriteActivity
import com.suhaili.gameinfoapp.di.FavouriteDependencies
import dagger.BindsInstance
import dagger.Component


@Component(dependencies = [FavouriteDependencies::class])
interface FavouriteComponent {

    fun inject(activity: FavouriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(mapsModuleDependencies: FavouriteDependencies): Builder
        fun build(): FavouriteComponent
    }

}