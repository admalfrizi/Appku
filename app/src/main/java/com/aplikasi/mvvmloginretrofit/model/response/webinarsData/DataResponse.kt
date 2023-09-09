package com.aplikasi.mvvmloginretrofit.model.response.webinarsData

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("data")
    val data: List<Data>,
    val meta: Meta?
)