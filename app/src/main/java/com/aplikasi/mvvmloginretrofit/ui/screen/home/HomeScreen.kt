package com.aplikasi.mvvmloginretrofit.ui.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplikasi.mvvmloginretrofit.databinding.ActivityHomeBinding
import com.aplikasi.mvvmloginretrofit.model.ClassModels
import com.aplikasi.mvvmloginretrofit.model.WebinarModels
import com.aplikasi.mvvmloginretrofit.ui.adapter.ClassAdapter
import com.aplikasi.mvvmloginretrofit.ui.adapter.TileAdapter
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import com.aplikasi.mvvmloginretrofit.util.profileNameInitials

class HomeScreen : Fragment() {
    private var _binding : ActivityHomeBinding? = null
    private val binding get() = _binding
    private var webinars = listOf(
        WebinarModels(1, "Webinar1", "Jumat, 28 Feb","20:30", "Gratis"),
        WebinarModels(2, "Webinar2", "Jumat, 28 Feb","20:30", "Gratis"),
        WebinarModels(3, "Webinar3", "Jumat, 28 Feb","20:30", "Gratis")
    )

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
        val view = binding!!.root

        setUser()
        setWebinarAdapter()
        setClassAdapter()

        return view
    }

    private fun setWebinarAdapter() {
        val webinarAdapter = TileAdapter(webinars.toMutableList())
        binding?.webinarRv!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding?.webinarRv!!.adapter = webinarAdapter
    }

    private fun setClassAdapter() {
        val classAdapter = ClassAdapter(classModel.toMutableList())
        binding?.classRv!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding?.classRv!!.adapter = classAdapter
    }

    private fun setUser() {
        val sessionManager = SessionManager(context)
        val user = sessionManager.getUser()
        if (user != null) {
            binding?.apply {
                tvName.text = user.name
                tvInisial.text = profileNameInitials(sessionManager)
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}