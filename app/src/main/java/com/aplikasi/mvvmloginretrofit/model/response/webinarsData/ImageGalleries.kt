package com.aplikasi.mvvmloginretrofit.model.response.webinarsData

import com.google.gson.annotations.SerializedName

data class ImageGalleries(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("webinar_id")
    val webinarId: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)
