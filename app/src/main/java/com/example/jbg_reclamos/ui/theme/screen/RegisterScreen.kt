package com.example.jbg_reclamos.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jbg_reclamos.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(onBack: () -> Unit) {
    val vm: AuthViewModel = viewModel()
    val error by vm.error.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var rut by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var comuna by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center) {
        Text("Registro", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))

        TextField(nombre, { nombre = it }, label = { Text("Nombre") })
        Spacer(Modifier.height(8.dp))
        TextField(rut, { rut = it }, label = { Text("RUT") })
        Spacer(Modifier.height(8.dp))
        TextField(email, { email = it }, label = { Text("Email") })
        Spacer(Modifier.height(8.dp))
        TextField(comuna, { comuna = it }, label = { Text("Comuna") })
        Spacer(Modifier.height(8.dp))
        TextField(telefono, { telefono = it }, label = { Text("Teléfono (opcional)") })
        Spacer(Modifier.height(8.dp))
        TextField(pass, { pass = it }, label = { Text("Contraseña") })
        Spacer(Modifier.height(8.dp))
        TextField(confirm, { confirm = it }, label = { Text("Confirmar contraseña") })

        Spacer(Modifier.height(16.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            vm.register(nombre, rut, email, comuna, telefono, pass, confirm) { onBack() }
        }) { Text("Registrarse") }

        TextButton(onClick = onBack) { Text("Volver al login") }
        error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        Spacer(Modifier.height(10.dp))
        Text("Admin: registra un correo que termine en @admin.cl")
    }
}
