package com.example.jbg_reclamos.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jbg_reclamos.ui.components.ClaimCard
import com.example.jbg_reclamos.viewmodel.ClaimsViewModel

@Composable
fun ClaimsListScreen(email: String) {

    // ✅ CONTEXT
    val context = LocalContext.current

    // ✅ VIEWMODEL CON FACTORY (OBLIGATORIO)
    val vm: ClaimsViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return ClaimsViewModel(context.applicationContext) as T
            }
        }
    )

    val claims by vm.claims.collectAsState()

    LaunchedEffect(email) {
        vm.load(email)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "Mis Reclamos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(12.dp))

        if (claims.isEmpty()) {
            Text("No tienes reclamos registrados.")
            return
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(claims) { c ->
                ClaimCard(
                    claim = c,
                    primaryActions = {},
                    destructiveAction = {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { vm.delete(c, email) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Icon(Icons.Default.Delete, null)
                            Spacer(Modifier.width(6.dp))
                            Text("Eliminar")
                        }
                    }
                )
            }
        }
    }
}
