package com.suhaili.gameinfoapp.core.resource.local


import androidx.room.*
import com.suhaili.gameinfoapp.core.resource.local.model.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDAO {
    @Query("SELECT * FROM gametable ORDER BY id ASC")
    fun getAllGame(): Flow<List<GameEntity>>

    @Query("SELECT * FROM gametable WHERE isFavorite = 1")
    fun getAllFavorite(): Flow<List<GameEntity>>

    @Query("SELECT * FROM gametable WHERE Game_Name LIKE '%' || :namegame || '%'")
    fun searchGame(namegame: String): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllResponse(value: List<GameEntity>)

    @Update
    fun updateItem(value: GameEntity)
}