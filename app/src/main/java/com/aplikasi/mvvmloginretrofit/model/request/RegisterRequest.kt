package com.aplikasi.tokenloginretrofit.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

    @SerializedName("name")
    var name: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("password")
    var password: String,
)
