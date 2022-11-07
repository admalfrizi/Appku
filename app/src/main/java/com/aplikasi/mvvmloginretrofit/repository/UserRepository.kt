package com.aplikasi.mvvmloginretrofit.repository

import android.util.Log
import android.widget.Toast
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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@Module
@InstallIn(ActivityRetainedComponent::class)
class UserRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    fun login(body : LoginRequest, isLogin: SessionManager) = flow {
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
            Log.d("TAG", "Error Server : " + e.message)
            emit(NetworkResult.error(e.message ?: "Terjadi Kesalahan", null))
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
                    emit(NetworkResult.error(it.message(), null))
                }
            }
        } catch (e: Exception) {
            Log.d("TAG", "Error Server : " + e.message)
            emit(NetworkResult.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun updateData(body: UpdateDataRequest, sessionManager: SessionManager) = flow {
        emit(NetworkResult.loading(null))
        try {
            remoteDataSource.updateData(body).let {
                if(it.isSuccessful){
                    val body = it.body()
                    val user = body?.data

                    if (user != null) {
                        sessionManager.setUser(user.User)
                    }

                    emit(NetworkResult.success(user))

                }
                else {
                    emit(NetworkResult.error(it.message(), null))
                }
            }
        } catch (e: Exception) {
            Log.d("TAG", "Error Server: " + e.message)
            emit(NetworkResult.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }
}