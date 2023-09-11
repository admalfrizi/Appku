package com.aplikasi.mvvmloginretrofit.model.response.kategoriKelasData


import com.google.gson.annotations.SerializedName

data class KategoriKelasDataResponse(
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("meta")
    val meta: Meta?
)