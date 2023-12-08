package com.example.lorby.model

data class RegistrationError (

    val status: Int,
    val message: String,
    val timestamp: String,

//    "status": 400,
//    "message": "Пользователь с таким именем уже существует",
//    "timestamp": "2023-12-08T05:27:50.970+00:00"
        )