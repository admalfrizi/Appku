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
import com.aplikasi.mvvmloginretrofit.R
import com.aplikasi.mvvmloginretrofit.api.State
import com.aplikasi.mvvmloginretrofit.databinding.ActivityHomeBinding
import com.aplikasi.mvvmloginretrofit.ui.adapter.ClassAdapter
import com.aplikasi.mvvmloginretrofit.ui.adapter.NewsAdapter
import com.aplikasi.mvvmloginretrofit.ui.adapter.TileAdapter
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import com.aplikasi.mvvmloginretrofit.util.profileNameInitials
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
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
    private val kelasAdapter by lazy {
        ClassAdapter()
    }

    private var categoryTitle = "Semua Kategori"
    private var categoryId = 0

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

        binding.categoryChip.setOnCheckedStateChangeListener { group, checkedIds ->
            val chip = group.findViewById<Chip>(checkedIds.first())
            val selectedCategoryName = chip.text.toString()

            Log.d("HomeScreen", selectedCategoryName)
            categoryTitle = selectedCategoryName

            if(selectedCategoryName == "Semua Kategori"){
                categoryId = 0
                generateKelasData(categoryId)
            }

            if(selectedCategoryName == "Mental"){
                categoryId = 1
                generateKelasData(categoryId)
            }

            if(selectedCategoryName == "Sosial"){
                categoryId = 2
                generateKelasData(categoryId)
            }

            if(selectedCategoryName == "Kehidupan"){
                categoryId = 3
                generateKelasData(categoryId)
            }
        }

        return view
    }

    private fun generateKelasData(categories : Int){
        homeViewModel.kelasWithCategory(categories).observe(viewLifecycleOwner){
            when(it.state) {
                State.SUCCESS -> {
                    val data = it.data
                    kelasAdapter.setData(data!!)
                    loadingKelasStop()
                }
                State.ERROR -> {
                    loadingKelasStop()
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("TAG", "Error : " + it.message)
                }
                State.LOADING -> {
                    binding.loadingKelasRv.startShimmer()
                }
            }
        }
    }

    private fun setWebinarAdapter() {
        binding.webinarRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.webinarRv.adapter = webinarAdapter
        binding.loadingWebinarsRv.startShimmer()
    }

    private fun setClassAdapter() {
        binding.kelasRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.kelasRv.adapter = kelasAdapter
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

        homeViewModel.kategoriKelas().observe(viewLifecycleOwner){
            when(it.state) {
                State.SUCCESS -> {
                    val data = it.data
                    for (item in data?.data!!){
                        val chipData = item?.categoryName
                        val chipDrawable = ChipDrawable.createFromAttributes(
                            requireContext(),
                            null,
                            0,
                            R.style.CustomChipStyle
                        )
                        val chip = Chip(context)

                        chip.setChipDrawable(chipDrawable)
                        chip.setTextAppearance(R.style.chipTextAppearance)
                        chip.text = chipData
                        chip.isChecked = true
                        chip.isCheckable = true
                        chip.isClickable = true
                        binding.categoryChip.addView(chip)
                    }
                    binding.categoryChip.visibility = View.VISIBLE
                    binding.categoryChip.check(R.id.all_categories)
                }
                State.ERROR -> {
                    loadingKelasStop()
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("TAG", "Error : " + it.message)
                }
                State.LOADING -> {
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

    private fun loadingKelasStop(){
        binding.loadingKelasRv.stopShimmer()
        binding.loadingKelasRv.visibility = View.GONE
        binding.kelasRv.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}