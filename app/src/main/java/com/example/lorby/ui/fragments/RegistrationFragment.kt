package com.example.lorby.ui.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lorby.R
import com.example.lorby.api.Repository
import com.example.lorby.databinding.FragmentRegisterBinding
import com.example.lorby.model.Data
import com.example.lorby.utils.Resource
import com.example.lorby.viewModel.RegViewModelProviderFactory
import com.example.lorby.viewModel.RegistrationViewModel
import com.google.android.material.color.ColorContrast


class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    lateinit var registrationViewModel: RegistrationViewModel
    var isEmailValid = false
    var isLoginValid = false
    var isPasswordValid = false
    var isPasswordRepeatValid = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val repository = Repository()
        val viewModelFactory = RegViewModelProviderFactory(repository)
        registrationViewModel = ViewModelProvider(this, viewModelFactory).get(RegistrationViewModel::class.java)

        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inputMail= binding.inputMail
        val inputUserName = binding.createUserName
        val inputPassword = binding.inputPassword
        val inputRepeatPassword = binding.inputRepeatPassword

        inputMail.addTextChangedListener(inputs)
        inputUserName.addTextChangedListener(inputs)
        inputPassword.addTextChangedListener(inputs)
        inputRepeatPassword.addTextChangedListener(inputs)

        binding.signUp.isEnabled = false
        binding.signUp.setBackgroundResource(R.drawable.background_signup)
        binding.signUp.setOnClickListener{

            registrationViewModel.newUser(inputMail.text.toString(), inputUserName.text.toString(), inputPassword.text.toString())
            observe()



        }
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

    }
    private fun observe() {
        registrationViewModel.userSaved.observe(viewLifecycleOwner, {userSaved->
            when(userSaved) {
                is Resource.Success -> {
                    findNavController().navigate(R.id.action_registerFragment_to_mailAuthFragment)
                }

                is Resource.Error -> {

                    Toast.makeText(requireContext(), "Пользователь не зарегестрирован", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    private fun updateButtonState() {
        if (isEmailValid && isLoginValid && isPasswordValid && isPasswordRepeatValid) {
            binding.signUp.isEnabled = true
            binding.signUp.setBackgroundResource(R.drawable.background_button)
            binding.signUp.setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.white))
        } else {
            binding.signUp.isEnabled = false
            binding.signUp.setBackgroundResource(R.drawable.background_signup)

        }
    }

    private val inputs = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
        override fun afterTextChanged(p0: Editable?) {
            val inputText = p0.toString()
            updateEmailTextColor(binding.inputMail.text.toString())
            checkLogin(binding.createUserName.text.toString())
            checkPassword(binding.inputPassword.text.toString())
            updateRepeatPasswordCheck(binding.inputPassword.text.toString(),binding.inputRepeatPassword.text.toString())
            updateButtonState()
        }

    }
    private fun  checkLogin(loginText: String){
        val mathesLogin = loginText.matches(Regex("[A-Za-z]*"))
        if(mathesLogin){
            isLoginValid = true
           binding.createUserName.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
        }else{
            isLoginValid = false
            binding.createUserName.setTextColor(ContextCompat.getColor(requireContext(),R.color.red))
        }
    }
    private fun checkPassword( passwordText: String){
//        PwlenghtColor
        if (passwordText.isEmpty()) {
            binding.pwlenght.setTextColor(ContextCompat.getColor(requireContext(), R.color.mediumDark))
        } else if (passwordText.length < 8) {
            isPasswordValid = false
            binding.pwlenght.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
        } else {
            isPasswordValid = true
            binding.pwlenght.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
//        PwCaseColor
        if (passwordText.isEmpty()) {
            binding.pwCase.setTextColor(ContextCompat.getColor(requireContext(), R.color.mediumDark))
        } else {
            val hasLowerCase = passwordText.any { it.isLowerCase() }
            val hasUpperCase = passwordText.any { it.isUpperCase() }
            if (hasLowerCase && hasUpperCase) {
                isPasswordValid = true
                binding.pwCase.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            } else {
                isPasswordValid = false
                binding.pwCase.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            }
        }
//        DigitColor
        if (passwordText.isEmpty()) {
            binding.pwDigit.setTextColor(ContextCompat.getColor(requireContext(), R.color.mediumDark))
        } else {
            val hasDigit = passwordText.any { it.isDigit() }

            if (hasDigit) {
                isPasswordValid = true
                binding.pwDigit.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            } else {
                isPasswordValid = false
                binding.pwDigit.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            }
        }
//        SpecialCharacterColor
        if (passwordText.isEmpty()) {
            binding.pwSpecialCharacter.setTextColor(ContextCompat.getColor(requireContext(), R.color.mediumDark))
        } else {
            val hasSpecialCharacter = passwordText.any { it.isSpecialCharacter() }

            if (hasSpecialCharacter) {
                isPasswordValid = true
                binding.pwSpecialCharacter.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            } else {
                isPasswordValid = false
                binding.pwSpecialCharacter.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            }
        }



    }
    private fun updateRepeatPasswordCheck(originalPassword: String, repeatedPassword: String) {
        val repeatPasswordCheck = binding.inputRepeatPassword
       if (originalPassword == repeatedPassword) {
           isPasswordRepeatValid  = true
            repeatPasswordCheck.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        } else {
            isPasswordRepeatValid =false
            repeatPasswordCheck.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
        }
    }
    private fun updateEmailTextColor(emailText: String) {
        if (!validEmail(emailText)) {
            isEmailValid = false
            binding.inputMail.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))

        } else {
            isEmailValid = true
            binding.inputMail.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        }
    }
    private fun Char.isSpecialCharacter(): Boolean {
        val specialCharacters = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~"
        return specialCharacters.contains(this)
    }
    private fun validEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    }




