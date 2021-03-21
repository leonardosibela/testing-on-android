package com.sibela.testingonandroid.data.remote.response

import com.google.gson.annotations.SerializedName

class ImageResult(
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,
    val comments: Int,
    val downloads: Int,
    val favorites: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,
    val views: Int,
    val webformatHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int
)