package com.example.lorby.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lorby.api.Repository
import com.example.lorby.model.RegistrationRequest
import com.example.lorby.utils.Resource
import kotlinx.coroutines.launch

class RegistrationViewModel(private  var repository: Repository) : ViewModel() {


    private val _userSaved :MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val userSaved: LiveData<Resource<Boolean>>
        get() = _userSaved

//    private val _userSaved: MutableLiveData<Resource<Boolean>> = MutableLiveData()
//    val userSaved: LiveData<Resource<Boolean>>
//        get() = _userSaved

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean>
        get() = _loginResult

    fun validateLogin(login: String, password: String): Boolean {

        val isValid = !login.isEmpty() && !password.isEmpty()
        _loginResult.value = isValid
        return isValid
    }
    fun newUser(email: String, username: String, password: String) {
        viewModelScope.launch {
            try {
                val userRequest = RegistrationRequest( username = username, email = email, password = password )
                val response = repository.registration(userRequest)
                if (response.isSuccessful) {
                    _userSaved.postValue(Resource.Loading())
                    val responseBody = response.body()
//                    saveUserSaved(true)
                    _userSaved.postValue(Resource.Success(true))
                    Log.d("Registration", "Successful: $responseBody")
                }else{
                    _userSaved.postValue(Resource.Error("Ошибка регистрации"))
                }
            } catch (e: Exception) {
                Log.e("MyViewModel", "Ошибка регистрации: ${e.message}")

                _userSaved.postValue(Resource.Error(e.message ?: "Ошибка регистрации"))
            }
        }
    }
}