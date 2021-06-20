package com.suhaili.gameinfoapp.core.di

import android.content.Context
import androidx.room.Room
import com.suhaili.gameinfoapp.core.BuildConfig
import com.suhaili.gameinfoapp.core.resource.local.GameDAO
import com.suhaili.gameinfoapp.core.resource.local.GameDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun keyDb(): SupportFactory {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.KEY_CRYPTO.toCharArray())
        return SupportFactory(passphrase)
    }


    @Singleton
    @Provides
    fun createDatabase(@ApplicationContext ctx: Context, key: SupportFactory): GameDB =
        Room.databaseBuilder(
            ctx,
            GameDB::class.java,
            "Game_Database.db"
        ).fallbackToDestructiveMigration().openHelperFactory(key).build()


    @Provides
    fun createGameDao(db: GameDB): GameDAO = db.gameDAO()

}