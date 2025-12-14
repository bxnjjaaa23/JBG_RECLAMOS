package com.example.jbg_reclamos.model.auth

data class UserDto(
    val id: Long,
    val nombre: String,
    val rut: String,
    val comuna: String,
    val telefono: String?,
    val email: String,
    val rol: String
)
