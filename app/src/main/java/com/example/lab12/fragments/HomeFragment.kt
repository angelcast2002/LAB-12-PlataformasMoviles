package com.example.lab12.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.lab12.R
import com.example.lab12.databinding.HomeLayoutBinding
import com.example.lab12.viewmodels.HomeViewModel
import com.example.lab12.viewmodels.SessionViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment: Fragment() {
    private lateinit var binding: HomeLayoutBinding
    private val sessionViewModel: SessionViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        setObservers()

    }

    private fun setObservers() {
        lifecycleScope.launch{
            sessionViewModel.validAuthToken.collectLatest { valueToken ->
                if (!valueToken){
                    requireView().findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToLoginFragment()
                    )
                }
            }
        }

        lifecycleScope.launch{
            homeViewModel.status.collectLatest {
                handleCheckInStatus(it)
            }
        }

    }

    private fun handleCheckInStatus(status: HomeViewModel.Status) {
        when(status){

            is HomeViewModel.Status.default -> {
                lifecycleScope.launch{
                    homeViewModel.progressBar.collectLatest { progressValue ->
                        if (!progressValue){
                            binding.progressHomeLayout.visibility = View.GONE
                            binding.imageHomeLayout.visibility = View.VISIBLE
                            binding.textStatusHomeLayout.visibility = View.VISIBLE
                            binding.apply {
                                btDefaultHomeLayout.isEnabled = true
                                btSuccesHomeLayout.isEnabled = true
                                btFailureHomeLayout.isEnabled = true
                                btEmptyHomeLayout.isEnabled = true
                                imageHomeLayout.setImageDrawable(
                                    ResourcesCompat.getDrawable(resources, R.drawable.ic_default, null)
                                )
                                textStatusHomeLayout.text = status.message
                            }
                        }

                        else {
                            binding.progressHomeLayout.visibility = View.VISIBLE
                            binding.imageHomeLayout.visibility = View.GONE
                            binding.textStatusHomeLayout.visibility = View.GONE
                            binding.apply {
                                btDefaultHomeLayout.isEnabled = true
                                btSuccesHomeLayout.isEnabled = false
                                btFailureHomeLayout.isEnabled = false
                                btEmptyHomeLayout.isEnabled = false
                                imageHomeLayout.setImageDrawable(
                                    ResourcesCompat.getDrawable(resources, R.drawable.ic_default, null)
                                )
                                textStatusHomeLayout.text = status.message
                            }
                        }
                    }
                }
            }

            is HomeViewModel.Status.empty -> {
                lifecycleScope.launch{
                    homeViewModel.progressBar.collectLatest { progressValue ->
                        if (!progressValue){
                            binding.progressHomeLayout.visibility = View.GONE
                            binding.imageHomeLayout.visibility = View.VISIBLE
                            binding.textStatusHomeLayout.visibility = View.VISIBLE
                            binding.apply {
                                btDefaultHomeLayout.isEnabled = true
                                btSuccesHomeLayout.isEnabled = true
                                btFailureHomeLayout.isEnabled = true
                                btEmptyHomeLayout.isEnabled = true
                                imageHomeLayout.setImageDrawable(
                                    ResourcesCompat.getDrawable(resources, R.drawable.ic_empty, null)
                                )
                                textStatusHomeLayout.text = status.message
                            }
                        }

                        else {
                            binding.progressHomeLayout.visibility = View.VISIBLE
                            binding.imageHomeLayout.visibility = View.GONE
                            binding.textStatusHomeLayout.visibility = View.GONE
                            binding.apply {
                                btDefaultHomeLayout.isEnabled = false
                                btSuccesHomeLayout.isEnabled = false
                                btFailureHomeLayout.isEnabled = false
                                btEmptyHomeLayout.isEnabled = true
                                imageHomeLayout.setImageDrawable(
                                    ResourcesCompat.getDrawable(resources, R.drawable.ic_empty, null)
                                )
                                textStatusHomeLayout.text = status.message
                            }
                        }
                    }
                }
            }

            is HomeViewModel.Status.failure -> {
                lifecycleScope.launch{
                    homeViewModel.progressBar.collectLatest { progressValue ->
                        if (!progressValue){
                            binding.progressHomeLayout.visibility = View.GONE
                            binding.imageHomeLayout.visibility = View.VISIBLE
                            binding.textStatusHomeLayout.visibility = View.VISIBLE
                            binding.apply {
                                btDefaultHomeLayout.isEnabled = true
                                btSuccesHomeLayout.isEnabled = true
                                btFailureHomeLayout.isEnabled = true
                                btEmptyHomeLayout.isEnabled = true
                                imageHomeLayout.setImageDrawable(
                                    ResourcesCompat.getDrawable(resources, R.drawable.ic_failure, null)
                                )
                                textStatusHomeLayout.text = status.message
                            }
                        }

                        else {
                            binding.progressHomeLayout.visibility = View.VISIBLE
                            binding.imageHomeLayout.visibility = View.GONE
                            binding.textStatusHomeLayout.visibility = View.GONE
                            binding.apply {
                                btDefaultHomeLayout.isEnabled = false
                                btSuccesHomeLayout.isEnabled = false
                                btFailureHomeLayout.isEnabled = true
                                btEmptyHomeLayout.isEnabled = false
                                imageHomeLayout.setImageDrawable(
                                    ResourcesCompat.getDrawable(resources, R.drawable.ic_failure, null)
                                )
                                textStatusHomeLayout.text = status.message
                            }
                        }
                    }
                }
            }

            is HomeViewModel.Status.succes -> {
                lifecycleScope.launch{
                    homeViewModel.progressBar.collectLatest { progressValue ->
                        if (!progressValue){
                            binding.progressHomeLayout.visibility = View.GONE
                            binding.imageHomeLayout.visibility = View.VISIBLE
                            binding.textStatusHomeLayout.visibility = View.VISIBLE
                            binding.apply {
                                btDefaultHomeLayout.isEnabled = true
                                btSuccesHomeLayout.isEnabled = true
                                btFailureHomeLayout.isEnabled = true
                                btEmptyHomeLayout.isEnabled = true
                                imageHomeLayout.setImageDrawable(
                                    ResourcesCompat.getDrawable(resources, R.drawable.ic_succes, null)
                                )
                                textStatusHomeLayout.text = status.message
                            }
                        }

                        else {
                            binding.progressHomeLayout.visibility = View.VISIBLE
                            binding.imageHomeLayout.visibility = View.GONE
                            binding.textStatusHomeLayout.visibility = View.GONE
                            binding.apply {
                                btDefaultHomeLayout.isEnabled = false
                                btSuccesHomeLayout.isEnabled = true
                                btFailureHomeLayout.isEnabled = false
                                btEmptyHomeLayout.isEnabled = false
                                imageHomeLayout.setImageDrawable(
                                    ResourcesCompat.getDrawable(resources, R.drawable.ic_succes, null)
                                )
                                textStatusHomeLayout.text = status.message
                            }
                        }
                    }
                }
            }
        }

    }

    private fun setListeners() {

        binding.apply {

            btSesionAbiertaHomeLayout.setOnClickListener{
                sessionViewModel.stop()
            }

            btCerrarSesionHomeLayout.setOnClickListener{
                sessionViewModel.stop()
                requireView().findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToLoginFragment()
                )
            }

            btDefaultHomeLayout.setOnClickListener{
                homeViewModel.Default()
            }

            btSuccesHomeLayout.setOnClickListener {
                homeViewModel.Succes()
            }

            btFailureHomeLayout.setOnClickListener {
                homeViewModel.Failure()
            }

            btEmptyHomeLayout.setOnClickListener {
                homeViewModel.Empty()
            }

        }
    }
}