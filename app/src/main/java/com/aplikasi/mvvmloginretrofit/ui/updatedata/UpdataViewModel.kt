package com.aplikasi.mvvmloginretrofit.ui.updatedata

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aplikasi.mvvmloginretrofit.model.request.UpdateDataRequest
import com.aplikasi.mvvmloginretrofit.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdataViewModel @Inject constructor(
    private val repo: UserRepository
): ViewModel(){

    fun updateData(data: UpdateDataRequest) = repo.updateData(data).asLiveData()
}