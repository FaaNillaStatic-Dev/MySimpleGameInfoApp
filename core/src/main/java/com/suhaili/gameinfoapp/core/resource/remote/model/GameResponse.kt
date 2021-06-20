package com.suhaili.gameinfoapp.core.resource.remote.model

import com.google.gson.annotations.SerializedName

data class GameResponse(

    @field:SerializedName("results")
    val results: List<ResultsItem>? = null
)

data class ShortScreenshotsItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)

data class TagsItem(

    @field:SerializedName("games_count")
    val gamesCount: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("language")
    val language: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("image_background")
    val imageBackground: String? = null,

    )

data class StoresItem(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("store")
    val store: Store? = null
)

data class ParentPlatformsItem(

    @field:SerializedName("platform")
    val platform: Platform? = null
)

data class ResultsItem(

    @field:SerializedName("stores")
    val stores: List<StoresItem>? = null,

    @field:SerializedName("rating")
    val rating: Double? = null,

    @field:SerializedName("short_screenshots")
    val shortScreenshots: List<ShortScreenshotsItem>? = null,

    @field:SerializedName("tags")
    val tags: List<TagsItem>? = null,

    @field:SerializedName("background_image")
    val backgroundImage: String? = null,

    @field:SerializedName("esrb_rating")
    val esrbRating: EsrbRating? = null,

    @field:SerializedName("rating_top")
    val ratingTop: Int? = null,

    @field:SerializedName("genres")
    val genres: List<GenresItem>? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatformsItem>? = null,

    @field:SerializedName("updated")
    val updated: String? = null,

    @field:SerializedName("released")
    val released: String? = null
)

data class EsrbRating(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    )

data class GenresItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("image_background")
    val imageBackground: String? = null,

    )

data class Store(

    @field:SerializedName("domain")
    val domain: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("image_background")
    val imageBackground: String? = null,
)

data class Platform(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    )
