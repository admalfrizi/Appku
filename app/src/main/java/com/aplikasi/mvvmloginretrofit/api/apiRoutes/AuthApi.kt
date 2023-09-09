package com.aplikasi.mvvmloginretrofit.api.apiRoutes

import com.aplikasi.mvvmloginretrofit.model.request.UpdateDataRequest
import com.aplikasi.mvvmloginretrofit.util.Constants
import com.aplikasi.tokenloginretrofit.request.LoginRequest
import com.aplikasi.tokenloginretrofit.request.RegisterRequest
import com.aplikasi.tokenloginretrofit.response.user.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {

    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body loginRequest: LoginRequest
    ): Response<UserResponse>

    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body regRequest: RegisterRequest
    ): Response<UserResponse>

    @POST(Constants.UPDATE_DATA)
    suspend fun updateData(
        @Path("id") id: Int,
        @Body updateReq : UpdateDataRequest
    ): Response<UserResponse>

    @POST(Constants.LOGOUT_URL)
    suspend fun logout(
        @Header("Authorization") authToken : String
    ): Response<UserResponse>

}