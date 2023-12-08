package com.example.lorby.api

import com.example.lorby.model.RegistrationRequest
import com.example.lorby.model.RegistrationResponse
import com.example.lorby.model.TokenRequest
import retrofit2.Retrofit

class Repository {
    suspend fun login(loginreq: TokenRequest) = RetrofitInstance.api.login(loginreq)
    suspend fun registration(requestRegistration: RegistrationRequest) = RetrofitInstance.api.registration(requestRegistration)
}