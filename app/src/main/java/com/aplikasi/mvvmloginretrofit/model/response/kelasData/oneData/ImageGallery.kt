package com.aplikasi.mvvmloginretrofit.model.response.kelasData.oneData


import com.google.gson.annotations.SerializedName

data class ImageGallery(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("kelas_id")
    val kelasId: Int?,
    @SerializedName("updated_at")
    val updatedAt: String?
)