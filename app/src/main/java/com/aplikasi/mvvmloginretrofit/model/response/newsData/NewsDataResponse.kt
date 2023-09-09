package com.aplikasi.mvvmloginretrofit.model.response.newsData


import com.google.gson.annotations.SerializedName

data class NewsDataResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("meta")
    val meta: Meta?
)