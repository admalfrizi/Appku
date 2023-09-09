package com.aplikasi.mvvmloginretrofit.ui.screen.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplikasi.mvvmloginretrofit.api.State
import com.aplikasi.mvvmloginretrofit.databinding.ActivityHomeBinding
import com.aplikasi.mvvmloginretrofit.model.ClassModels
import com.aplikasi.mvvmloginretrofit.ui.adapter.ClassAdapter
import com.aplikasi.mvvmloginretrofit.ui.adapter.NewsAdapter
import com.aplikasi.mvvmloginretrofit.ui.adapter.TileAdapter
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import com.aplikasi.mvvmloginretrofit.util.profileNameInitials
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : Fragment() {
    private val homeViewModel by viewModels<HomeViewModel>()
    private var _binding : ActivityHomeBinding? = null
    private val binding get() = _binding!!
    private val webinarAdapter by lazy {
        TileAdapter()
    }
    private val newsAdapter by lazy {
        NewsAdapter()
    }

    private var classModel = listOf(
        ClassModels(1, "Kelas1", "Pemula",12),
        ClassModels(2, "Kelas2", "Menengah",8),
        ClassModels(3, "Kelas3", "Pemula",5)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root

        setUser()
        setWebinarAdapter()
        setClassAdapter()
        setNewsAdapter()
        setDataApi()

        return view
    }

    private fun setWebinarAdapter() {
        binding.webinarRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.webinarRv.adapter = webinarAdapter
        binding.loadingWebinarsRv.startShimmer()
    }

    private fun setClassAdapter() {
        val classAdapter = ClassAdapter(classModel.toMutableList())
        binding.classRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.classRv.adapter = classAdapter
    }

    private fun setNewsAdapter(){
        binding.newsRv.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL,false)
        binding.newsRv.adapter = newsAdapter
        binding.loadingNewsRv.startShimmer()
    }

    private fun setDataApi() {
        homeViewModel.webinarsList().observe(viewLifecycleOwner){
            when(it.state) {
                State.SUCCESS -> {
                    val data = it.data
                    webinarAdapter.setData(data!!)
                    loadingWebinarsStop()
                }
                State.ERROR -> {
                    loadingWebinarsStop()
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("TAG", "Error : " + it.message)
                }
                State.LOADING -> {
                    binding.loadingWebinarsRv.startShimmer()
                }
            }
        }

        homeViewModel.newsList().observe(viewLifecycleOwner){
            when(it.state) {
                State.SUCCESS -> {
                    val data = it.data
                    newsAdapter.setData(data!!)
                    loadingNewsStop()
                }
                State.ERROR -> {
                    loadingNewsStop()
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("TAG", "Error : " + it.message)
                }
                State.LOADING -> {
                    binding.loadingNewsRv.startShimmer()
                }
            }
        }
    }

    private fun setUser() {
        val sessionManager = SessionManager(context)
        val user = sessionManager.getUser()
        if (user != null) {
            binding.apply {
                tvName.text = user.name
                tvInisial.text = profileNameInitials(sessionManager)
            }
        }
    }

    private fun loadingWebinarsStop(){
        binding.loadingWebinarsRv.stopShimmer()
        binding.loadingWebinarsRv.visibility = View.GONE
        binding.webinarRv.visibility = View.VISIBLE
    }

    private fun loadingNewsStop(){
        binding.loadingNewsRv.stopShimmer()
        binding.loadingNewsRv.visibility = View.GONE
        binding.newsRv.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}