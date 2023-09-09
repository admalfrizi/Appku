package com.aplikasi.mvvmloginretrofit.model.response.newsData


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)