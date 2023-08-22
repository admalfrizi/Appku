package com.aplikasi.mvvmloginretrofit.ui.screen.home

import androidx.lifecycle.ViewModel
import com.aplikasi.mvvmloginretrofit.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repo : UserRepository
) : ViewModel() {
}