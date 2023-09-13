package com.aplikasi.mvvmloginretrofit.model.response.webinarsData.oneData


import com.google.gson.annotations.SerializedName

data class OneWebinarDataResponse(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("meta")
    val meta: Meta?
)