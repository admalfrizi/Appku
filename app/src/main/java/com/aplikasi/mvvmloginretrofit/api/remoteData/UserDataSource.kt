package com.aplikasi.mvvmloginretrofit.api.remoteData

import com.aplikasi.mvvmloginretrofit.api.apiRoutes.AuthApi
import com.aplikasi.mvvmloginretrofit.model.request.UpdateDataRequest
import com.aplikasi.tokenloginretrofit.request.LoginRequest
import com.aplikasi.tokenloginretrofit.request.RegisterRequest
import javax.inject.Inject

class UserDataSource @Inject constructor(private val api : AuthApi) {

     suspend fun login(body : LoginRequest) = api.login(body)

     suspend fun register(body: RegisterRequest) = api.register(body)

     suspend fun updateData(body: UpdateDataRequest) = api.updateData(body.id,body)

     suspend fun logout(token: String) = api.logout(token)
}