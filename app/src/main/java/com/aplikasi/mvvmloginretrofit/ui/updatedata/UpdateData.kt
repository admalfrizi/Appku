package com.aplikasi.mvvmloginretrofit.ui.updatedata

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aplikasi.mvvmloginretrofit.api.State
import com.aplikasi.mvvmloginretrofit.databinding.ActivityUpdateDataBinding
import com.aplikasi.mvvmloginretrofit.model.request.UpdateDataRequest
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class UpdateData : AppCompatActivity() {

    private val updateViewModel: UpdateViewModel by viewModels()
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
            updateData()
        }

        binding.btnBack.setOnClickListener{
            super.onBackPressed()
        }

        binding.imageProfile.setOnClickListener {

        }
    }
    private fun setProfileData() {
        val user = SessionManager(this).getUser()
        if(user != null) {
            binding.apply {
                idView.text = user.id.toString()
                userEdt.setText(user.name)
                emailEdt.setText(user.email)
            }
        }

        binding.idView.visibility = View.GONE
    }

    private fun updateData() {
        if(binding.userEdt.text.toString().isEmpty()) return
        if(binding.emailEdt.text.toString().isEmpty()) return

        val sessionManager = SessionManager(this)
        val body = UpdateDataRequest(
            binding.idView.text.toString().toInt(),
            binding.userEdt.text.toString(),
            binding.emailEdt.text.toString()
        )

        updateViewModel.updateData(body, sessionManager).observe(this) {
            when(it.state) {
                State.SUCCESS -> {
                    Toast.makeText(
                        applicationContext,
                        "Updated Data",
                        Toast.LENGTH_LONG
                    ).show()
                    super.onBackPressed()
                }
                State.ERROR ->{
                    _binding?.ld!!.visibility = View.GONE
                    Toast.makeText(
                        applicationContext,
                        "error : " + it.message,
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("TAG", "Error : " + it.message)
                }
                State.LOADING -> {
                    _binding?.ld!!.visibility = View.VISIBLE
                }
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}

