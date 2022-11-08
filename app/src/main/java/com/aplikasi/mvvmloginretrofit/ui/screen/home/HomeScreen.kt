package com.aplikasi.mvvmloginretrofit.ui.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aplikasi.mvvmloginretrofit.databinding.ActivityHomeBinding
import com.aplikasi.mvvmloginretrofit.util.SessionManager

class HomeScreen : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var binding : ActivityHomeBinding? = null
    private val _binding get() = binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding!!.root

        setUser()

        return view
    }

    fun setUser() {
        val sessionManager = SessionManager(context)
        val user = sessionManager.getUser()
        if (user != null) {
            binding?.apply {
                tvName.text = user.name
            }
        }
    }

}