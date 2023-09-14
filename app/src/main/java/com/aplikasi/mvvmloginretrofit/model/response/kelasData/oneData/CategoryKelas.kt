package com.aplikasi.mvvmloginretrofit.model.response.kelasData.oneData


import com.google.gson.annotations.SerializedName

data class CategoryKelas(
    @SerializedName("categoryName")
    val categoryName: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("updated_at")
    val updatedAt: String?
)