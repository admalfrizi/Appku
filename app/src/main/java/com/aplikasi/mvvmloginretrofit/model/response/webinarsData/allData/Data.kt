package com.aplikasi.mvvmloginretrofit.model.response.webinarsData.allData

import com.google.gson.annotations.SerializedName
data class Data(
    @SerializedName("image_galleries")
    val imageGalleries: List<ImageGalleries>,
    @SerializedName("created_at")
    val created_at: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("freeOrBuy")
    val freeOrBuy: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("titleWebinar")
    val titleWebinar: String?,
    @SerializedName("updated_at")
    val updated_at: String?
)