package com.aplikasi.mvvmloginretrofit.repository

import android.util.Log
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import com.aplikasi.mvvmloginretrofit.api.NetworkResult
import com.aplikasi.mvvmloginretrofit.api.RemoteDataSource
import com.aplikasi.mvvmloginretrofit.model.request.UpdateDataRequest
import com.aplikasi.tokenloginretrofit.request.LoginRequest
import com.aplikasi.tokenloginretrofit.request.RegisterRequest
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Module
@InstallIn(ActivityRetainedComponent::class)
class UserRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    fun login(body : LoginRequest, isLogin: SessionManager) = flow{
        emit(NetworkResult.loading(null))
        try{
            remoteDataSource.login(body).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    isLogin.setSession(true)
                    isLogin.saveToken(body?.data!!.accessToken)

                    if (user != null) {
                        isLogin.setUser(user.User)
                    }

                    emit(NetworkResult.success(user))
                    Log.d("TAG", "Berhasil : $body")
                } else  {
                    emit(NetworkResult.error(it.message(), null))
                }
            }
        } catch (e: Exception){
            emit(NetworkResult.error("Terjadi Kesalahan : " + e.message,null))
            Log.d("TAG", "Error : " + e.message)
        }
    }

    fun register(body: RegisterRequest, isReg: SessionManager) = flow {
        emit(NetworkResult.loading(null))
        try {
            remoteDataSource.register(body).let {
                if(it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data

                    if (user != null) {
                        isReg.setUser(user.User)
                    }
                    
                    emit(NetworkResult.success(user))
                } else  {
                    emit(NetworkResult.error(it.errorBody().toString(), null))
                }
            }
        } catch (e: Exception) {
            emit(NetworkResult.error(e.message ?: "Terjadi Kesalahan",null))
        }
    }

    fun updateData(body: UpdateDataRequest) = flow {
        emit(NetworkResult.loading(null))
        try {
            remoteDataSource.updateData(body).let {
                if(it.isSuccessful){
                    val body = it.body()
                    val user = body?.data

                }
            }
        } catch (e: Exception) {

        }
    }
}