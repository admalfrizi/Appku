package com.aplikasi.mvvmloginretrofit.api.apiRoutes

import com.aplikasi.mvvmloginretrofit.model.response.kategoriKelasData.KategoriKelasDataResponse
import com.aplikasi.mvvmloginretrofit.model.response.kelasData.KelasDataResponse
import com.aplikasi.mvvmloginretrofit.model.response.newsData.NewsDataResponse
import com.aplikasi.mvvmloginretrofit.model.response.webinarsData.allData.WebinarDataResponse
import com.aplikasi.mvvmloginretrofit.model.response.webinarsData.oneData.OneWebinarDataResponse
import com.aplikasi.mvvmloginretrofit.util.Constants
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface CoreApi {
    @POST(Constants.WEBINAR_DATA_LIST)
    suspend fun webinarsData(): Response<WebinarDataResponse>

    @POST(Constants.NEWS_DATA_LIST)
    suspend fun newsData(): Response<NewsDataResponse>

    @POST(Constants.KATEGORI_KELAS_DATA_LIST)
    suspend fun kelasKategoriData(): Response<KategoriKelasDataResponse>

    @POST(Constants.KELAS_DATA_LIST)
    suspend fun kelasData(
        @Query("categories") categories: Int
    ): Response<KelasDataResponse>

    @POST(Constants.WEBINAR_DATA_LIST)
    suspend fun oneWebinarData(
        @Query("id") id: Int
    ): Response<OneWebinarDataResponse>
}