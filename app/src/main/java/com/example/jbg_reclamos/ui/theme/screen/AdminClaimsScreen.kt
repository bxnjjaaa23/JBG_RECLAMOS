package com.example.jbg_reclamos.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jbg_reclamos.ui.components.ClaimCard
import com.example.jbg_reclamos.viewmodel.AdminViewModel

@Composable
fun AdminClaimsScreen() {

    val vm: AdminViewModel = viewModel()
    val claims by vm.claims.collectAsState()

    LaunchedEffect(Unit) { vm.loadAll() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "Panel Administrador",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(claims) { c ->
                ClaimCard(
                    claim = c,
                    showOwner = true,
                    primaryActions = {

                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { vm.changeStatus(c.id, "RESUELTO") }
                        ) {
                            Icon(Icons.Default.CheckCircle, null)
                            Spacer(Modifier.width(6.dp))
                            Text("Resolver")
                        }

                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            onClick = { vm.changeStatus(c.id, "NO_RESUELTO") }
                        ) {
                            Icon(Icons.Default.Close, null)
                            Spacer(Modifier.width(6.dp))
                            Text("No resuelto")
                        }
                    },
                    destructiveAction = {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { vm.delete(c) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Icon(Icons.Default.Delete, null)
                            Spacer(Modifier.width(6.dp))
                            Text("Eliminar reclamo")
                        }
                    }
                )
            }
        }
    }
}
