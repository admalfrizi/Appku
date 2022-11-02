package com.aplikasi.mvvmloginretrofit.ui.navbottom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.aplikasi.mvvmloginretrofit.R
import com.aplikasi.mvvmloginretrofit.databinding.ActivityBottomNavBinding
import com.aplikasi.mvvmloginretrofit.ui.auth.LoginScreen
import com.aplikasi.mvvmloginretrofit.ui.screen.home.MainActivity
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNav : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var sessionManager: SessionManager
    private lateinit var menu : Menu
    private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(MainActivity())
        sessionManager = SessionManager(this)

        navSetup()
    }

    private fun setFragment(fr : Fragment) {
        val frag = supportFragmentManager.beginTransaction()
        frag.replace(R.id.view_layout, fr)
        frag.commit()
    }

    private fun navSetup() {
        bottomNavigationView = binding.navBtm
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true


        if(sessionManager.getStatusLogin()) {
            bottomNavigationView.setOnItemSelectedListener { menu ->
                when(menu.itemId) {
                    R.id.home -> {
                        setFragment(MainActivity())
                        true
                    }
                    R.id.find -> {
                        setFragment(MainActivity())
                        true
                    }
                    R.id.chat -> {
                        setFragment(MainActivity())
                        true
                    }
                    R.id.settings -> {
                        setFragment(MainActivity())
                        true
                    }
                    else -> false
                }
            }
        } else {
            Intent(this, LoginScreen::class.java)
        }

    }
}