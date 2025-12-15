package com.example.jbg_reclamos.ui.screen

import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.jbg_reclamos.ui.theme.screen.ProfileScreen


@Composable
fun HomeScreen(
    email: String,
    role: String,
    onLogout: () -> Unit
) {
    var tab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = tab == 0,
                    onClick = { tab = 0 },
                    label = { Text("Reclamos") },
                    icon = {}
                )
                NavigationBarItem(
                    selected = tab == 1,
                    onClick = { tab = 1 },
                    label = { Text("Crear") },
                    icon = {}
                )
                NavigationBarItem(
                    selected = tab == 2,
                    onClick = { tab = 2 },
                    label = { Text("Perfil") },
                    icon = {}
                )

                if (role.uppercase() == "ADMIN") {
                    NavigationBarItem(
                        selected = tab == 3,
                        onClick = { tab = 3 },
                        label = { Text("Admin") },
                        icon = {}
                    )
                }
            }
        }
    ) { _ ->
        when (tab) {
            0 -> ClaimsListScreen(email = email)
            1 -> CreateClaimScreen(email = email)
            2 -> ProfileScreen(email = email, role = role, onLogout = onLogout)
            3 -> AdminClaimsScreen()
        }
    }
}
