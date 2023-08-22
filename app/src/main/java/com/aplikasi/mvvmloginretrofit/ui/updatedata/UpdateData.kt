package com.aplikasi.mvvmloginretrofit.ui.updatedata

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aplikasi.mvvmloginretrofit.R
import com.aplikasi.mvvmloginretrofit.api.State
import com.aplikasi.mvvmloginretrofit.databinding.ActivityUpdateDataBinding
import com.aplikasi.mvvmloginretrofit.model.request.UpdateDataRequest
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import com.aplikasi.mvvmloginretrofit.util.profileNameInitials
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateData : AppCompatActivity() {

    private val updateViewModel: UpdateViewModel by viewModels()
    private var _binding : ActivityUpdateDataBinding? = null
    private val binding get() = _binding!!
    //private var imageProfile : File? = null

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
        val data = SessionManager(this)
        val user = data.getUser()
        if(user != null) {
            binding.apply {
                idView.text = user.id.toString()
                userEdt.setText(user.name)
                emailEdt.setText(user.email)
                tvInisial.text = profileNameInitials(data)
            }
        }

        binding.idView.visibility = View.GONE
    }

    private fun updateData() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.custom_loading_window)

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
                    dialog.dismiss()
                }
                State.ERROR ->{
                    dialog.dismiss()
                    Toast.makeText(
                        applicationContext,
                        "error : " + it.message,
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("TAG", "Error : " + it.message)
                }
                State.LOADING -> {
                    dialog.show()
                }
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}

