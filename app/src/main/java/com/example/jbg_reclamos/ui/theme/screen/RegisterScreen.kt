package com.example.jbg_reclamos.ui.theme.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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

    var passVisible by remember { mutableStateOf(false) }
    var confirmVisible by remember { mutableStateOf(false) }
    var localError by remember { mutableStateOf<String?>(null) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

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

        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = if (passVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passVisible = !passVisible }) {
                    Icon(
                        imageVector = if (passVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = confirm,
            onValueChange = { confirm = it },
            label = { Text("Confirmar contraseña") },
            singleLine = true,
            visualTransformation = if (confirmVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { confirmVisible = !confirmVisible }) {
                    Icon(
                        imageVector = if (confirmVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            }
        )

        Spacer(Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                when {
                    pass.length < 6 ->
                        localError = "La contraseña debe tener al menos 6 caracteres"

                    pass != confirm ->
                        localError = "Las contraseñas no coinciden"

                    else -> {
                        localError = null
                        vm.register(
                            nombre,
                            rut,
                            email,
                            comuna,
                            telefono,
                            pass,
                            confirm
                        ) {
                            onBack()
                        }
                    }
                }
            }
        ) {
            Text("Registrarse")
        }

        TextButton(onClick = onBack) {
            Text("Volver al login")
        }

        localError?.let {
            Spacer(Modifier.height(6.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        error?.let {
            Spacer(Modifier.height(6.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}
