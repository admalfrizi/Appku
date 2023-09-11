package com.aplikasi.mvvmloginretrofit.model.response.kelasData


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("image_galleries")
    val imageGalleries: List<ImageGalleries>,
    @SerializedName("category_kelas")
    val categoryKelas: Category?,
    @SerializedName("category_id")
    val categoryId: Int?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("jmlhVideo")
    val jmlhVideo: Int?,
    @SerializedName("stage")
    val stage: String?,
    @SerializedName("titleKelas")
    val titleKelas: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)