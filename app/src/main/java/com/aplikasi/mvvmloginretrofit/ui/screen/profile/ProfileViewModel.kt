package com.aplikasi.mvvmloginretrofit.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aplikasi.mvvmloginretrofit.repository.UserRepository
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo : UserRepository
): ViewModel() {

    fun logout(token: String, isLogout : SessionManager) = repo.logout(token, isLogout).asLiveData()

}