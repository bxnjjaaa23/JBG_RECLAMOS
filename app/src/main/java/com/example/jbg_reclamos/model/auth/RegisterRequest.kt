package com.example.jbg_reclamos.model.auth

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("rut")
    val rut: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("comuna")
    val comuna: String,

    // si es null, NO se envía
    @SerializedName("telefono")
    val telefono: String? = null,

    @SerializedName("password")
    val password: String
)
