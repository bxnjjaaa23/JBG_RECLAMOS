package com.example.jbg_reclamos.data.network

import com.example.jbg_reclamos.model.auth.LoginRequest
import com.example.jbg_reclamos.model.auth.LoginResponse
import com.example.jbg_reclamos.model.auth.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    )

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse
}
