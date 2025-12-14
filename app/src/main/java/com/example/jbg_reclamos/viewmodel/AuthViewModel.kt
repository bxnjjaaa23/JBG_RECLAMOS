package com.example.jbg_reclamos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jbg_reclamos.data.local.SessionManager
import com.example.jbg_reclamos.data.network.ApiClient
import com.example.jbg_reclamos.data.network.AuthApi
import com.example.jbg_reclamos.model.auth.LoginRequest
import com.example.jbg_reclamos.model.auth.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(app: Application) : AndroidViewModel(app) {

    private val api = ApiClient.retrofit.create(AuthApi::class.java)
    private val session = SessionManager(app)

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _loggedIn = MutableStateFlow(false)
    val loggedIn: StateFlow<Boolean> = _loggedIn

    fun register(
        nombre: String,
        rut: String,
        email: String,
        comuna: String,
        telefono: String,
        pass: String,
        confirm: String,
        onSuccess: () -> Unit
    ) {
        if (nombre.isBlank() || rut.isBlank() || email.isBlank() || comuna.isBlank() || pass.isBlank() || confirm.isBlank()) {
            _error.value = "Completa todos los campos obligatorios"
            return
        }

        if (pass != confirm) {
            _error.value = "Las contraseñas no coinciden"
            return
        }

        viewModelScope.launch {
            try {
                val request = RegisterRequest(
                    nombre = nombre,
                    rut = rut,
                    email = email,
                    comuna = comuna,
                    telefono = telefono.takeIf { it.isNotBlank() },
                    password = pass
                )

                api.register(request)

                _error.value = null
                onSuccess()

            } catch (e: Exception) {
                _error.value = "Registro falló (API). Revisa si el correo ya existe."
            }
        }
    }


    fun login(email: String, pass: String) {
        if (email.isBlank() || pass.isBlank()) {
            _error.value = "Email y contraseña obligatorios"
            return
        }

        viewModelScope.launch {
            try {
                val response = api.login(
                    LoginRequest(
                        email = email.trim(),
                        password = pass
                    )
                )

                session.save(
                    email = response.usuario.email,
                    token = response.token,
                    role = response.usuario.rol
                )

                _error.value = null
                _loggedIn.value = true

            } catch (e: Exception) {
                _error.value = "Login falló (credenciales incorrectas)"
            }
        }
    }


    fun logout() {
        viewModelScope.launch {
            session.clear()
            _loggedIn.value = false
        }
    }
}
