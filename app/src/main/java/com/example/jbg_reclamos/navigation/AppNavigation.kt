package com.example.jbg_reclamos.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jbg_reclamos.data.local.SessionManager
import com.example.jbg_reclamos.ui.screen.HomeScreen
import com.example.jbg_reclamos.ui.screen.LoginScreen
import com.example.jbg_reclamos.ui.screen.RegisterScreen
import com.example.jbg_reclamos.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.first

@Composable
fun AppNavigation() {
    val authVm: AuthViewModel = viewModel()
    val loggedIn by authVm.loggedIn.collectAsState()

    val context = LocalContext.current
    val session = remember { SessionManager(context) }

    var showRegister by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf<String?>(null) }
    var role by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(loggedIn) {
        email = session.emailFlow.first()
        role = session.roleFlow.first()
    }

    if (email == null || !loggedIn) {
        if (showRegister) {
            RegisterScreen(
                onBack = { showRegister = false }
            )
        } else {
            LoginScreen(
                onGoRegister = { showRegister = true }
            )
        }
    } else {
        HomeScreen(
            email = email!!,
            role = role ?: "cliente",
            onLogout = { authVm.logout(); email = null; role = null }
        )
    }
}
