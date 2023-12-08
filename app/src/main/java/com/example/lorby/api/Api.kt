package com.example.lorby.api

import com.example.lorby.model.RegistrationRequest
import com.example.lorby.model.RegistrationResponse
import com.example.lorby.model.TokenRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("/auth/authenticate/")
    suspend fun login( @Body tokenRequest: TokenRequest): Response<TokenRequest>

    @POST("/auth/registration")
    suspend fun registration( @Body registration:RegistrationRequest):Response<RegistrationResponse>
}