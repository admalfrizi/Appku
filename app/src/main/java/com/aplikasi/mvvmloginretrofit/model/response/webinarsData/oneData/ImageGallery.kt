package com.aplikasi.mvvmloginretrofit.model.response.webinarsData.oneData


import com.google.gson.annotations.SerializedName

data class ImageGallery(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("webinar_id")
    val webinarId: Int?
)