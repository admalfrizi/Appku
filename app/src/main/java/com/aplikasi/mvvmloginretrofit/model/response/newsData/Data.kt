package com.aplikasi.mvvmloginretrofit.model.response.newsData


import com.google.gson.annotations.SerializedName
data class Data(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("descNews")
    val descNews: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("nameNews")
    val nameNews: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)