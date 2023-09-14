package com.aplikasi.mvvmloginretrofit.ui.details

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.aplikasi.mvvmloginretrofit.R
import com.aplikasi.mvvmloginretrofit.api.State
import com.aplikasi.mvvmloginretrofit.databinding.ActivityKelasDetailsScreenBinding
import com.aplikasi.mvvmloginretrofit.ui.adapter.KelasImageAdapter
import com.aplikasi.mvvmloginretrofit.ui.screen.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KelasDetailsScreen : AppCompatActivity() {
    private val homeViewModel by viewModels<HomeViewModel>()
    private var _binding : ActivityKelasDetailsScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var kelasImageAdapter: KelasImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityKelasDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.third))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val id = intent.getIntExtra("id", 0)
        val titleKelas = intent.getStringExtra("titleKelas")
        val stage = intent.getStringExtra("stage")
        val currentPageIndex = 1

        binding.titleKelas.text = titleKelas
        binding.jmlhVideo.text = stage

        kelasImageAdapter = KelasImageAdapter(id)
        binding.imgKelasRv.apply {
            adapter = kelasImageAdapter
            currentItem = currentPageIndex
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        setDataImageApi(id)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setDataImageApi(id: Int){
        homeViewModel.oneKelas(id).observe(this){
            when(it.state) {
                State.SUCCESS -> {
                    val data = it.data
                    kelasImageAdapter.setData(data!!)
                }
                State.ERROR -> {
                    Toast.makeText(
                        this,
                        it.data?.meta!!.message,
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("TAG", "Error : " + it.message)
                }
                State.LOADING -> {
                }
            }
        }
    }

}