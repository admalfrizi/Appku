package com.aplikasi.mvvmloginretrofit.repository

import android.util.Log
import com.aplikasi.mvvmloginretrofit.api.NetworkResult
import com.aplikasi.mvvmloginretrofit.api.RemoteDataSource
import com.aplikasi.mvvmloginretrofit.model.request.UpdateDataRequest
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import com.aplikasi.tokenloginretrofit.request.LoginRequest
import com.aplikasi.tokenloginretrofit.request.RegisterRequest
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@Module
@InstallIn(ActivityRetainedComponent::class)
class UserRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    fun login(body : LoginRequest, isLogin: SessionManager) = flow {
        emit(NetworkResult.loading(null))
        remoteDataSource.login(body).let {
            if (it.isSuccessful){
                val body = it.body()
                val user = body?.data
                isLogin.setSession(true)
                isLogin.saveToken(body?.data!!.accessToken)
                isLogin.setUser(user!!.User)
                emit(NetworkResult.success(user))
                Log.d("TAG", "Berhasil : $body")
            } else  {
                emit(NetworkResult.error(it.message(), null))
            }
        }
    }.catch { e -> emit(NetworkResult.error(e.message ?: "Terjadi Kesalahan", null)) }.flowOn(IO)

    fun register(body: RegisterRequest, isReg: SessionManager) = flow {
        emit(NetworkResult.loading(null))
        try {
            remoteDataSource.register(body).let {
                if(it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data

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
    }.catch { e -> emit(NetworkResult.error(e.message ?: "Terjadi Kesalahan", null)) }

    fun logout(token: String, sessionManager: SessionManager) = flow {
        emit(NetworkResult.loading(null))
        remoteDataSource.logout(token).let {
            if(it.isSuccessful){
                val body = it.body()
                val user = body?.data

                sessionManager.setSession(false)
                sessionManager.deleteToken()
                sessionManager.setUser(null)

                emit(NetworkResult.success(user))
            }
            else {
                emit(NetworkResult.error(it.message(), null))
            }
        }
    }.catch { e -> emit(NetworkResult.error(e.message ?: "Terjadi Kesalahan", null)) }
}