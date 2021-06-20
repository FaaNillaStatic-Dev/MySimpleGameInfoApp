package com.suhaili.gameinfoapp.core.resource.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "GameTable")
data class GameEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id_game: Int? = null,

    @ColumnInfo(name = "Game_Name")
    var game_name: String? = null,

    @ColumnInfo(name = "release")
    var release_game: String? = null,

    @ColumnInfo(name = "background_image")
    var backgroundImage: String? = null,

    @ColumnInfo(name = "rating")
    var rating: Double? = null,

    @ColumnInfo(name = "ratingTop")
    var rating_top: Int? = null,

    @ColumnInfo(name = "updated")
    var updated: String? = null,

    @ColumnInfo(name = "esrb_rating")
    var esrb_rating: String? = null,

    @ColumnInfo(name = "genre")
    var genre_game: String? = null,

    @ColumnInfo(name = "screen_shoot")
    var screenShoot: String? = null,

    @ColumnInfo(name = "tags")
    var tags: String? = null,

    @ColumnInfo(name = "store")
    var store: String? = null,

    @ColumnInfo(name = "platform")
    var platform: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Int? = 0
) : Parcelable