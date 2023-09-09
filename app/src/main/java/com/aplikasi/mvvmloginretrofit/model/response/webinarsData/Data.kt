package com.aplikasi.mvvmloginretrofit.model.response.webinarsData

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    @SerializedName("created_at")
    val created_at: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("freeOrBuy")
    val freeOrBuy: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("titleWebinar")
    val titleWebinar: String?,
    @SerializedName("updated_at")
    val updated_at: String?
) : Parcelable