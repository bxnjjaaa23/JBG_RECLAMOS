package com.example.jbg_reclamos.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jbg_reclamos.viewmodel.AuthViewModel

@Composable
fun LoginScreen(onGoRegister: () -> Unit) {
    val vm: AuthViewModel = viewModel()
    val error by vm.error.collectAsState()

    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))

        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(Modifier.height(8.dp))
        TextField(value = pass, onValueChange = { pass = it }, label = { Text("Contraseña") })

        Spacer(Modifier.height(16.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = { vm.login(email, pass) }) {
            Text("Iniciar sesión")
        }

        TextButton(onClick = onGoRegister) { Text("Crear cuenta") }

        error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        Spacer(Modifier.height(10.dp))
        Text("Admin: usa un correo que termine en @admin.cl")
    }
}
