package com.aplikasi.mvvmloginretrofit.model.response.newsData

import com.google.gson.annotations.SerializedName

data class ImageGalleries(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("news_id")
    val newsId: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)
