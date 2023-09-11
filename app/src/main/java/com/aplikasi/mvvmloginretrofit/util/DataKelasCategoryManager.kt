package com.aplikasi.mvvmloginretrofit.util

import android.content.Context
import android.content.SharedPreferences
import com.aplikasi.mvvmloginretrofit.model.KategoriKelas
import com.google.gson.Gson


class DataKelasCategoryManager(context: Context?) {

    private val preferences: SharedPreferences = context!!.getSharedPreferences("Appku Data Kategori", Context.MODE_PRIVATE)

    companion object {
        const val KATEGORI_KELAS = "kategori_kelas"
    }

    fun setKategoriKelas(value: String){
        val data: String = Gson().toJson(value, KategoriKelas::class.java)
        preferences.edit().putString(KATEGORI_KELAS, data).apply()
    }

    fun getKategoriKelas(): KategoriKelas? {
        val data: String = preferences.getString(KATEGORI_KELAS, null) ?: return null
        return Gson().fromJson(data, KategoriKelas::class.java)
    }
}