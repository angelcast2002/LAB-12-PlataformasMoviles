package com.example.lab12.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.lab12.databinding.HomeLayoutBinding
import com.example.lab12.databinding.LoginLayoutBinding
import com.example.lab12.viewmodels.SessionViewModel

class HomeFragment: Fragment() {
    private lateinit var binding: HomeLayoutBinding
    private val sessionViewModel: SessionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }
}