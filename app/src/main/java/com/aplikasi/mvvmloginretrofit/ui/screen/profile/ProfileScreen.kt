package com.aplikasi.mvvmloginretrofit.ui.screen.profile

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aplikasi.mvvmloginretrofit.R
import com.aplikasi.mvvmloginretrofit.api.State
import com.aplikasi.mvvmloginretrofit.databinding.ActivityProfileBinding
import com.aplikasi.mvvmloginretrofit.model.MenuList
import com.aplikasi.mvvmloginretrofit.ui.adapter.MenuAdapter
import com.aplikasi.mvvmloginretrofit.ui.auth.LoginScreen
import com.aplikasi.mvvmloginretrofit.ui.screen.profile.menu.ChangeDataProfileScreen
import com.aplikasi.mvvmloginretrofit.ui.updatedata.UpdateData
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import com.aplikasi.mvvmloginretrofit.util.profileNameInitials
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileScreen : Fragment() {

    private val profileViewModel by viewModels<ProfileViewModel>()
    private var _binding: ActivityProfileBinding? = null
    private val binding get() = _binding
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = _binding!!.root

        sessionManager = SessionManager(context)

        onResume()
        setLoginStatus()
        dataPersonalMenuAdapter()
        servicesMenuAdapter()

        return view
    }

    private fun dataPersonalMenuAdapter() {
        val menuList = listOf(
            MenuList(R.drawable.ic_change_data,"Ubah Profile Anda", UpdateData::class.java),
            MenuList(R.drawable.key_ic,"Ubah Kata Sandi Anda", ChangeDataProfileScreen::class.java),
            MenuList(R.drawable.your_class,"Kelas Kamu", ChangeDataProfileScreen::class.java)
        )

        val adapter = MenuAdapter(requireContext(), menuList)

        binding?.dataPersonalMenu!!.adapter = adapter
    }

    private fun servicesMenuAdapter(){
        val menuList = listOf(
            MenuList(R.drawable.qna_ic,"Tanya Jawab", ChangeDataProfileScreen::class.java),
            MenuList(R.drawable.form_suggestion,"Formulir Masukan Anda", ChangeDataProfileScreen::class.java),
            MenuList(R.drawable.like_ic,"Beri Nilai Aplikasi", ChangeDataProfileScreen::class.java)
        )

        val adapter = MenuAdapter(requireContext(), menuList)

        binding?.servicesMenu!!.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logoutButton()
        super.onViewCreated(view, savedInstanceState)
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

        val ld = dialog.findViewById<ProgressBar>(R.id.ld_logout)
        val txtLogout = dialog.findViewById<TextView>(R.id.logoutTxt)
        val btn = dialog.findViewById<LinearLayout>(R.id.buttons)
        val btnClose = dialog.findViewById<Button>(R.id.btnCancel)
        val btnLogout = dialog.findViewById<Button>(R.id.btnLogout)

        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        btnLogout.setOnClickListener {
            val token = sessionManager.fetchToken().toString()

            profileViewModel.logout(token, sessionManager).observe(viewLifecycleOwner){
                when (it.state) {
                    State.SUCCESS -> {
                        Intent(requireContext(), LoginScreen::class.java).also {
                            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(it)
                        }
                        dialog.dismiss()
                    }
                    State.ERROR -> {
                        ld.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            it.message,
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d("TAG", "Error : " + it.message)
                    }
                    State.LOADING -> {
                        ld.visibility = View.VISIBLE
                        txtLogout.visibility = View.GONE
                        btn.visibility = View.GONE
                    }
                }
            }
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

    private fun setUser() {
        val user = sessionManager.getUser()
        if (user != null) {
            binding?.apply {
                tvName.text = user.name
                tvInisial.text = profileNameInitials(sessionManager)
                tvId.text = user.id.toString()
            }
        }

        binding!!.tvId.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}