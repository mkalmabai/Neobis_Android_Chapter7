package com.example.lorby.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lorby.R
import com.example.lorby.api.Repository
import com.example.lorby.databinding.FragmentLoginBinding
import com.example.lorby.utils.Resource
import com.example.lorby.utils.showSnackbar
import com.example.lorby.viewModel.RegViewModelProviderFactory
import com.example.lorby.viewModel.RegistrationViewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: RegistrationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation()
        val repository = Repository()
        val viewModelFactory = RegViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RegistrationViewModel::class.java)

        viewModel.loginResult.observe(viewLifecycleOwner, Observer { isValid ->
            if (!isValid) {
                showSnackbar.showCustomSnackbar(requireContext(),binding.root,"Заполните все поля")
            } else {
            }
        })

        binding.signIn.setOnClickListener {
            val login = binding.inputLogin.text.toString()
            val password = binding.inputPassword.text.toString()
            viewModel.validateLogin(login, password)
            viewModel.login(login, password)
            observe()

        }

    }
    private fun observe() {
        viewModel.tokens.observe(viewLifecycleOwner) { tokens ->
            when (tokens) {
                is Resource.Success -> {
                    val welcomeback = getString(R.string.welcomeback)
                    val action = LoginFragmentDirections.actionLoginFragment2ToMainFragment2(welcomeback)
                    findNavController().navigate(action)
                }
                is Resource.Error -> {
                    showSnackbar.showCustomSnackbar(
                        requireContext(),
                        binding.root,
                        "Неверный логин или пароль"
                    )
                }
                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun navigation() {
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_registerFragment)
        }
    }


}