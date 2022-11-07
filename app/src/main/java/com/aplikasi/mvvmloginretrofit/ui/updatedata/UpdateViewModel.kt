package com.aplikasi.mvvmloginretrofit.ui.updatedata

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aplikasi.mvvmloginretrofit.model.request.UpdateDataRequest
import com.aplikasi.mvvmloginretrofit.repository.UserRepository
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val repo: UserRepository
): ViewModel(){

    fun updateData(data: UpdateDataRequest, sessionManager: SessionManager) = repo.updateData(data, sessionManager).asLiveData()
}