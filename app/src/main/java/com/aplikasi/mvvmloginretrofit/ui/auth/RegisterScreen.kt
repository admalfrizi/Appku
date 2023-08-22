package com.aplikasi.mvvmloginretrofit.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aplikasi.mvvmloginretrofit.api.State
import com.aplikasi.mvvmloginretrofit.databinding.ActivityRegisterScreenBinding
import com.aplikasi.tokenloginretrofit.request.RegisterRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterScreen : AppCompatActivity() {
    private val regviewModel: AuthViewModel by viewModels()
    private var binding : ActivityRegisterScreenBinding? = null
    private val _binding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterScreenBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        regBtn()

    }

    private fun regBtn() {
        _binding.regBtn.setOnClickListener {
            register()
        }

        _binding.toSignin.setOnClickListener {
            Intent(this, LoginScreen::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
                finish()
            }
        }
    }

    private fun register() {
        val body = RegisterRequest(
            _binding.userEdt.text.toString(),
            _binding.emailEdt.text.toString(),
            _binding.pwEdt.text.toString()
        )

        regviewModel.register(body).observe(this) {
            when(it.state) {
                State.SUCCESS -> {
                    Toast.makeText(
                        applicationContext,
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("RegisterFragment", "message : ${it.message}")
                    Intent(applicationContext, LoginScreen::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }
                State.ERROR -> {
                    _binding.ld.visibility = View.GONE
                    Toast.makeText(
                        applicationContext,
                        "error : " + it.message,
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("TAG", "Error : " + it.message)
                }
                State.LOADING -> {
                    _binding.ld.visibility = View.VISIBLE
                }
            }
        }

    }
}