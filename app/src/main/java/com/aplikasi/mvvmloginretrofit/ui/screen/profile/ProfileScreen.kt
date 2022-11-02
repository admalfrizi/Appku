package com.aplikasi.mvvmloginretrofit.ui.screen.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import com.aplikasi.mvvmloginretrofit.databinding.ActivityProfileBinding
import com.aplikasi.mvvmloginretrofit.ui.auth.LoginScreen

class ProfileScreen : Fragment() {

    private lateinit var viewModel: ProfileViewModel
    private var _binding: ActivityProfileBinding? = null
    private val binding get() = _binding
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = _binding!!.root


        sessionManager = SessionManager(context)

        onResume()
        logoutButton()

        return view
    }

    private fun logoutButton() {
        _binding?.btnLogout!!.setOnClickListener {
            sessionManager.deleteToken()
            sessionManager.setSession(false)
            val intent = Intent(context, LoginScreen::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        setUser()
        setLoginStatus()
        super.onResume()
    }

    private fun setLoginStatus() {
        if(!sessionManager.getStatusLogin()){
            val intent = Intent(context, LoginScreen::class.java)
            startActivity(intent)
        }
    }

    fun setUser() {
        val user = sessionManager.getUser()

        if (user != null) {
            binding?.apply {
                tvName.text = user.name
                tvEmail.text = user.email
            }
        }
    }
}