package com.aplikasi.mvvmloginretrofit.ui.screen.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aplikasi.mvvmloginretrofit.databinding.ActivityChatScreenBinding

class ChatScreen : Fragment() {

    private lateinit var viewModel: ChatViewModel
    private var binding: ActivityChatScreenBinding? =null
    private val _binding get() = binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = ActivityChatScreenBinding.inflate(layoutInflater)
        val view = binding!!.root
        return view
    }

}