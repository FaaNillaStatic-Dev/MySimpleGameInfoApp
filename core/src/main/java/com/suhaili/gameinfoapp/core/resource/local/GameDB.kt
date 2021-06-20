package com.suhaili.gameinfoapp.core.resource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.suhaili.gameinfoapp.core.resource.local.model.GameEntity


@Database(entities = [GameEntity::class], version = 1)
abstract class GameDB : RoomDatabase() {
    abstract fun gameDAO(): GameDAO

}
