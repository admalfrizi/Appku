package com.aplikasi.mvvmloginretrofit.di

import android.content.Context
import com.aplikasi.mvvmloginretrofit.api.remoteData.UserDataSource
import com.aplikasi.mvvmloginretrofit.util.Constants
import com.aplikasi.mvvmloginretrofit.api.RequestInterceptor
import com.aplikasi.mvvmloginretrofit.api.apiRoutes.AuthApi
import com.aplikasi.mvvmloginretrofit.api.apiRoutes.CoreApi
import com.aplikasi.mvvmloginretrofit.api.remoteData.CoreDataSource
import com.aplikasi.mvvmloginretrofit.repository.DataRepository
import com.aplikasi.mvvmloginretrofit.repository.UserRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideInterceptor() : HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

    @Singleton
    @Provides
    fun provideHttpClient(
        context: Context,
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(RequestInterceptor(context))
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideBaseUrl(): String {
        return Constants.BASE_URL
    }

    @Singleton
    @Provides
    fun provideService(
        okHttpClient: OkHttpClient,
        baseUrl : String
    ): AuthApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(okHttpClient)
            .build()
            .create(AuthApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCoreService(
        okHttpClient: OkHttpClient,
        baseUrl : String
    ): CoreApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(okHttpClient)
            .build()
            .create(CoreApi::class.java)
    }

    @Provides
    fun provideAuthData(authApi: AuthApi) : UserDataSource {
        return UserDataSource(authApi)
    }

    @Provides
    fun provideUserRepo(userDataSource: UserDataSource) :UserRepository{
        return UserRepository(userDataSource)
    }

    @Provides
    fun provideCoreData(coreApi : CoreApi): CoreDataSource {
        return CoreDataSource(coreApi)
    }

    @Provides
    fun provideCoreRepo(coreDataSource: CoreDataSource) : DataRepository {
        return DataRepository(coreDataSource)
    }

}