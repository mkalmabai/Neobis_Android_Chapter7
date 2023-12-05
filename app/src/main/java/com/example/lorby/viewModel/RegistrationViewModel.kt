package com.example.lorby.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean>
        get() = _loginResult

    fun validateLogin(login: String, password: String): Boolean {
        // Check if the login and password are not empty
        val isValid = !login.isEmpty() && !password.isEmpty()
        _loginResult.value = isValid
        return isValid
    }
}