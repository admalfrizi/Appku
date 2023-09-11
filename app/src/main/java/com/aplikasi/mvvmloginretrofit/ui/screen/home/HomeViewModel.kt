package com.aplikasi.mvvmloginretrofit.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aplikasi.mvvmloginretrofit.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo : DataRepository
) : ViewModel() {
    fun webinarsList() = repo.getWebinarsList().asLiveData()

    fun newsList() = repo.getNewsList().asLiveData()

    fun kategoriKelas() = repo.getKategoriKelasList().asLiveData()

    fun kelasWithCategory(category: Int) = repo.getKelasWithCategoryList(category).asLiveData()
}