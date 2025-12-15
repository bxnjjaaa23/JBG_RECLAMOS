package com.example.jbg_reclamos.ui.theme.screen


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(email: String, role: String, onLogout: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Perfil", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))
        Text("Email: $email")
        Text("Rol: $role")
        Spacer(Modifier.height(16.dp))
        Button(onClick = onLogout) { Text("Cerrar sesión") }
        Spacer(Modifier.height(8.dp))
    }
}
