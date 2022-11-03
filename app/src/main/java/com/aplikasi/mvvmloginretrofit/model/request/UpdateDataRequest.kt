package com.aplikasi.mvvmloginretrofit.model.request

data class UpdateDataRequest(
    val id: Int,
    val name: String? = null,
    val email: String? = null
)
