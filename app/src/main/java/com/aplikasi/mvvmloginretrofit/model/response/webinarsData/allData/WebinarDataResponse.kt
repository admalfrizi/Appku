package com.aplikasi.mvvmloginretrofit.model.response.webinarsData.allData

import com.google.gson.annotations.SerializedName

data class WebinarDataResponse(
    @SerializedName("data")
    val data: List<Data>,
    val meta: Meta?
)