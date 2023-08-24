package com.aplikasi.mvvmloginretrofit.ui.screen.profile.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aplikasi.mvvmloginretrofit.databinding.ChangeDataProfileScreenBinding

class ChangeDataProfileScreen : AppCompatActivity() {

    private lateinit var binding: ChangeDataProfileScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChangeDataProfileScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}