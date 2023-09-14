package com.aplikasi.mvvmloginretrofit.model.response.kelasData.oneData


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("category_id")
    val categoryId: Int?,
    @SerializedName("category_kelas")
    val categoryKelas: CategoryKelas?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_galleries")
    val imageGalleries: List<ImageGallery>,
    @SerializedName("jmlhVideo")
    val jmlhVideo: Int?,
    @SerializedName("stage")
    val stage: String?,
    @SerializedName("titleKelas")
    val titleKelas: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)