package com.example.lorby.model

data class RegistrationResponse(
    val id:Int,
    val surname :String,
    val name:String,
    val birthdayDay:String,
    val phoneNumber:String,
    val username:String,
    val email:String,
)
