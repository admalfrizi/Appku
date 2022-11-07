package com.aplikasi.mvvmloginretrofit.ui.screen.profile

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.fragment.app.Fragment
import com.aplikasi.mvvmloginretrofit.R
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import com.aplikasi.mvvmloginretrofit.databinding.ActivityProfileBinding
import com.aplikasi.mvvmloginretrofit.ui.auth.LoginScreen
import com.aplikasi.mvvmloginretrofit.ui.updatedata.UpdateData

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
        updateBtn()
        setLoginStatus()

        return view
    }

    private fun updateBtn() {
        _binding?.btnUpdate!!.setOnClickListener {
            val intent = Intent(context, UpdateData::class.java)
            startActivity(intent)
        }
    }

    private fun logoutButton() {
        _binding?.btnLogout!!.setOnClickListener {
            dialogLogout()
        }
    }

    private fun dialogLogout() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.custom_popup_window)

        val btnClose = dialog.findViewById<Button>(R.id.btnCancel)
        val btnLogout = dialog.findViewById<Button>(R.id.btnLogout)

        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        btnLogout.setOnClickListener {
            sessionManager.deleteToken()
            sessionManager.setSession(false)
            val intent = Intent(context, LoginScreen::class.java)
            startActivity(intent)

        }

        dialog.show()
    }

    override fun onResume() {
        setUser()
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