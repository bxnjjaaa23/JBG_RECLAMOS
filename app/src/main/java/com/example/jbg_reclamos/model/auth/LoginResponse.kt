package com.example.jbg_reclamos.model.auth

data class LoginResponse(
    val mensaje: String,
    val usuario: UserDto,
    val token: String
)
