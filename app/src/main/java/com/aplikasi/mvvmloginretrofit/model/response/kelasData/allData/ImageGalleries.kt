package com.aplikasi.mvvmloginretrofit.model.response.kelasData.allData

import com.google.gson.annotations.SerializedName

data class ImageGalleries(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("kelas_id")
    val kelasId: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)
