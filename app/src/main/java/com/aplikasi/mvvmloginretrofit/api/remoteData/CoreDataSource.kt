package com.aplikasi.mvvmloginretrofit.api.remoteData

import com.aplikasi.mvvmloginretrofit.api.apiRoutes.CoreApi
import javax.inject.Inject

class CoreDataSource @Inject constructor(private val api : CoreApi){

    suspend fun getWebinars() = api.webinarsData()

    suspend fun getNews() = api.newsData()

    suspend fun getKelasKategori() = api.kelasKategoriData()

    suspend fun getKelasWithCategory(category : Int) = api.kelasData(category)
}