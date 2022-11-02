package com.aplikasi.mvvmloginretrofit.ui.screen.find

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aplikasi.mvvmloginretrofit.R
import com.aplikasi.mvvmloginretrofit.databinding.ActivityFindBinding

class FindScreen : Fragment() {

    private lateinit var viewModel: FindViewModel
    private var binding : ActivityFindBinding? = null
    private val _binding get() = binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        super.onCreate(savedInstanceState)
        binding =  ActivityFindBinding.inflate(layoutInflater)
        val view = binding!!.root

        return view
    }


}