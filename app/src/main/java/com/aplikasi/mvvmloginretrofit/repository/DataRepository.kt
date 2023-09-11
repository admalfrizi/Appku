package com.aplikasi.mvvmloginretrofit.repository

import android.util.Log
import com.aplikasi.mvvmloginretrofit.api.NetworkResult
import com.aplikasi.mvvmloginretrofit.api.remoteData.CoreDataSource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
@Module
@InstallIn(ActivityRetainedComponent::class)
class DataRepository @Inject constructor(private val coreDataSource: CoreDataSource) {

    fun getWebinarsList() = flow {
        emit(NetworkResult.loading(null))
        coreDataSource.getWebinars().let {
            if (it.isSuccessful){
                val body = it.body()
                val webinars = body

                emit(NetworkResult.success(webinars))
                Log.d("TAG", "Berhasil : $body")

            } else  {
                emit(NetworkResult.error(it.message(), null))
            }
        }
    }.catch { e -> emit(NetworkResult.error(e.message ?: "Terjadi Kesalahan", null)) }.flowOn(
        Dispatchers.IO
    )

    fun getNewsList() = flow {
        emit(NetworkResult.loading(null))
        coreDataSource.getNews().let {
            if (it.isSuccessful){
                val newsData = it.body()

                emit(NetworkResult.success(newsData))
                Log.d("TAG", "Berhasil : $newsData")

            } else  {
                emit(NetworkResult.error(it.message(), null))
            }
        }
    }.catch { e -> emit(NetworkResult.error(e.message ?: "Terjadi Kesalahan", null)) }.flowOn(
        Dispatchers.IO
    )

    fun getKategoriKelasList() = flow {
        emit(NetworkResult.loading(null))
        coreDataSource.getKelasKategori().let {
            if (it.isSuccessful){
                val kategoriKelasData = it.body()

                emit(NetworkResult.success(kategoriKelasData))
                Log.d("TAG", "Berhasil : $kategoriKelasData")

            } else  {
                emit(NetworkResult.error(it.message(), null))
            }
        }
    }.catch { e -> emit(NetworkResult.error(e.message ?: "Terjadi Kesalahan", null)) }.flowOn(
        Dispatchers.IO
    )

    fun getKelasWithCategoryList(category: Int) = flow {
        emit(NetworkResult.loading(null))
        coreDataSource.getKelasWithCategory(category).let {
            if (it.isSuccessful){
                val kategoriKelasData = it.body()

                emit(NetworkResult.success(kategoriKelasData))
                Log.d("TAG", "Berhasil : $kategoriKelasData")

            } else  {
                emit(NetworkResult.error(it.message(), null))
            }
        }
    }.catch { e -> emit(NetworkResult.error(e.message ?: "Terjadi Kesalahan", null)) }.flowOn(
        Dispatchers.IO
    )
}