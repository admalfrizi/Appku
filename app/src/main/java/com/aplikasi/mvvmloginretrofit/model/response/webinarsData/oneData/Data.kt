package com.aplikasi.mvvmloginretrofit.model.response.webinarsData.oneData


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("freeOrBuy")
    val freeOrBuy: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_galleries")
    val imageGalleries: List<ImageGallery>,
    @SerializedName("titleWebinar")
    val titleWebinar: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)