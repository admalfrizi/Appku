package com.aplikasi.mvvmloginretrofit.ui.updatedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.aplikasi.mvvmloginretrofit.api.State
import com.aplikasi.mvvmloginretrofit.databinding.ActivityUpdateDataBinding
import com.aplikasi.mvvmloginretrofit.model.request.UpdateDataRequest
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import java.io.File

class UpdateData : AppCompatActivity() {

    private val updateViewModel: UpdataViewModel by viewModels()
    private var _binding : ActivityUpdateDataBinding? = null
    private val binding get() = _binding!!
    private var imageProfile : File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setProfileData()
        setBtn()
    }

    private fun setBtn() {
        binding.btnChange.setOnClickListener {
            if(imageProfile == null) {
                updateData()
            }
        }

        binding.imageProfile.setOnClickListener {

        }
    }

    private fun updateData() {
        if(binding.userEdt.text.toString().isEmpty()) return
        if(binding.emailEdt.text.toString().isEmpty()) return

        val userId = SessionManager(this).getUser()?.id
        val body = userId?.let {
                UpdateDataRequest(
                    id = it,
                    name = binding.userEdt.text.toString(),
                    email = binding.emailEdt.text.toString()
                )
            }

        if (body != null) {
            updateViewModel.updateData(body).observe(this) {
                when(it.state) {
                    State.SUCCESS -> {

                    }
                    State.ERROR ->{

                    }
                    State.LOADING -> {

                    }
                }
            }
        }
    }

    private fun setProfileData() {
        val user = SessionManager(this).getUser()
        if(user != null) {
            binding.apply {
                userEdt.setText(user.name)
                emailEdt.setText(user.email)
            }
        }
    }
}