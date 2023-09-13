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
import com.aplikasi.mvvmloginretrofit.databinding.ActivityWebinarDetailScreenBinding
import com.aplikasi.mvvmloginretrofit.ui.adapter.WebinarImageAdapter
import com.aplikasi.mvvmloginretrofit.ui.screen.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebinarDetailScreen : AppCompatActivity() {
    private val homeViewModel by viewModels<HomeViewModel>()
    private var _binding : ActivityWebinarDetailScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var webinarImageAdapter: WebinarImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWebinarDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.third))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val id = intent.getIntExtra("id", 0)
        val name = intent.getStringExtra("name")
        val jam = intent.getStringExtra("jam")
        val tgl = intent.getStringExtra("tgl")
        val freeOrBuy = intent.getStringExtra("freeOrBuy")


        webinarImageAdapter = WebinarImageAdapter(id)
        binding.imgWebinarRv.apply {
            adapter = webinarImageAdapter
        }

        binding.titleWebinar.text = name
        binding.jamWebinar.text = jam

        Log.d("TAGIdDetails", id.toString())


        setImageApi(id)

        val currentPageIndex = 1
        binding.imgWebinarRv.currentItem = currentPageIndex
        binding.imgWebinarRv.orientation = ViewPager2.ORIENTATION_HORIZONTAL

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setImageApi(id: Int) {
        homeViewModel.oneWebinar(id).observe(this){
            when(it.state) {
                State.SUCCESS -> {
                    val data = it.data
                    webinarImageAdapter.setData(data!!)
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