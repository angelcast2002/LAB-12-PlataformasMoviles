package com.example.lab12.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.lab12.R
import com.example.lab12.databinding.LoginLayoutBinding
import com.example.lab12.viewmodels.SessionViewModel
import kotlinx.coroutines.flow.collectLatest

class LoginFragment: Fragment() {

    private lateinit var binding: LoginLayoutBinding
    private val sessionViewModel: SessionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCorrectDataUser()
        setListeners()
        setObservers()
    }

    private fun setCorrectDataUser() {
        binding.apply {
            val correctData = textCorreoPasswordLoginLayout.text.toString()
            sessionViewModel.setCorrectDataUser(correctData)
        }
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            sessionViewModel.logger.collectLatest {
                handleCheckInStatus(it)
            }
        }
    }

    private fun handleCheckInStatus(status: SessionViewModel.logStatus) {
        when(status){

            is SessionViewModel.logStatus.failure -> {
                binding.apply {
                    progressUsers.visibility = View.GONE
                    buttonIniciarSesionLoginfragment.visibility = View.VISIBLE
                }
                Toast.makeText(getActivity(), getString(R.string.textToast), Toast.LENGTH_LONG).show()
            }
            is SessionViewModel.logStatus.checkData -> {
                binding.apply {
                    progressUsers.visibility = View.VISIBLE
                    buttonIniciarSesionLoginfragment.visibility = View.GONE
                }

            }
            is SessionViewModel.logStatus.notLogged -> {
                binding.apply {
                    progressUsers.visibility = View.GONE
                    buttonIniciarSesionLoginfragment.visibility = View.VISIBLE
                }
            }

            is SessionViewModel.logStatus.logged -> {
                sessionViewModel.regresiveCount()
                requireView().findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                )
            }
        }
    }


    private fun setListeners() {
        binding.buttonIniciarSesionLoginfragment.setOnClickListener{
            binding.apply {
                val correo = textInputCorreoTextLoginFragmentEditText.text.toString()
                val password = textInputContrasenaTextLoginFragmentEditText.text.toString()
                sessionViewModel.setDataUser(correo, password)
            }
        }
    }
}