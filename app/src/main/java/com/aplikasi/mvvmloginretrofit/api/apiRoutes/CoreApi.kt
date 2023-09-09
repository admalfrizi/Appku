package com.aplikasi.mvvmloginretrofit.api.apiRoutes

import com.aplikasi.mvvmloginretrofit.model.response.newsData.NewsDataResponse
import com.aplikasi.mvvmloginretrofit.model.response.webinarsData.DataResponse
import com.aplikasi.mvvmloginretrofit.util.Constants
import retrofit2.Response
import retrofit2.http.POST

interface CoreApi {
    @POST(Constants.WEBINAR_DATA_LIST)
    suspend fun webinarsData(): Response<DataResponse>

    @POST(Constants.NEWS_DATA_LIST)
    suspend fun newsData(): Response<NewsDataResponse>
}