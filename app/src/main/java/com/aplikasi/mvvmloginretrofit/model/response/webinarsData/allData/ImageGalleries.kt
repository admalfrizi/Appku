package com.aplikasi.mvvmloginretrofit.model.response.webinarsData.allData

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageGalleries(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("webinar_id")
    val webinarId: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
) : Parcelable
