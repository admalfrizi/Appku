package com.aplikasi.mvvmloginretrofit.model.response.kelasData.oneData


import com.google.gson.annotations.SerializedName

data class OneKelasDataResponse(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("meta")
    val meta: Meta?
)