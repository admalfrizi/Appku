package com.aplikasi.mvvmloginretrofit.model.response.kelasData


import com.google.gson.annotations.SerializedName

data class KelasDataResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("meta")
    val meta: Meta?
)