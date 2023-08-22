package com.aplikasi.mvvmloginretrofit.ui.auth

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aplikasi.mvvmloginretrofit.R
import com.aplikasi.mvvmloginretrofit.api.State
import com.aplikasi.mvvmloginretrofit.databinding.ActivityLoginScreenBinding
import com.aplikasi.mvvmloginretrofit.ui.navbottom.BottomNav
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import com.aplikasi.tokenloginretrofit.request.LoginRequest
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginScreen() : AppCompatActivity() {

    private val loginviewModel: AuthViewModel by viewModels()
    private var _binding : ActivityLoginScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val sessionManager = SessionManager(this)


        if(sessionManager.getStatusLogin()){
            startActivity(Intent(this, BottomNav::class.java))
        }
        else {
            binding.loginBtn.setOnClickListener {
                if(binding.emailEdt.text.isNullOrEmpty() && binding.pwEdt.text.isNullOrEmpty()){
                    Toast.makeText(
                        applicationContext,
                        "Maaf Data Anda Harus Di isi",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    loadingDialog(sessionManager)
                }
            }
        }

        binding.toSignup.setOnClickListener {
            Intent(this, RegisterScreen::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
                finish()
            }
        }
    }

    private fun loadingDialog(sessionManager: SessionManager){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.custom_loading_window)

        val body = LoginRequest(binding.emailEdt.text.toString(), binding.pwEdt.text.toString())

        loginviewModel.login(body, sessionManager).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    Intent(this, BottomNav::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                        finish()
                        dialog.dismiss()
                    }
                }
                State.ERROR -> {
                    dialog.dismiss()
                    Toast.makeText(
                        applicationContext,
                        it.message,
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

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}