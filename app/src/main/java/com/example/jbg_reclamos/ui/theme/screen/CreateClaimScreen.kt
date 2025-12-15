package com.example.jbg_reclamos.ui.screen

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jbg_reclamos.viewmodel.ClaimsViewModel
import com.google.android.gms.location.LocationServices

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateClaimScreen(email: String) {

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

    val error by vm.error.collectAsState()
    val scrollState = rememberScrollState()

    var product by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val problemTypes = listOf(
        "Producto dañado",
        "No llegó",
        "Falla de fábrica",
        "Cobro incorrecto",
        "Otro"
    )

    var selectedType by remember { mutableStateOf(problemTypes[0]) }
    var expanded by remember { mutableStateOf(false) }

    // 📸 IMAGEN
    var imageUri by remember { mutableStateOf<String?>(null) }
    var tempUri by remember { mutableStateOf<android.net.Uri?>(null) }

    val takePictureLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempUri != null) {
            imageUri = tempUri.toString()
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            val uri = createImageUri(context)
            tempUri = uri
            takePictureLauncher.launch(uri)
        }
    }

    // 📍 UBICACIÓN
    var latitude by remember { mutableStateOf<Double?>(null) }
    var longitude by remember { mutableStateOf<Double?>(null) }
    var address by remember { mutableStateOf<String?>(null) }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
        if (granted) {
            val fused = LocationServices.getFusedLocationProviderClient(context)
            fused.lastLocation.addOnSuccessListener { loc ->
                if (loc != null) {
                    latitude = loc.latitude
                    longitude = loc.longitude
                    address = getAddressFromLocation(
                        context,
                        loc.latitude,
                        loc.longitude
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {

        Text("Crear Reclamo", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))

        TextField(
            value = product,
            onValueChange = { product = it },
            label = { Text("Nombre del producto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedType,
                onValueChange = {},
                readOnly = true,
                label = { Text("Tipo de problema") },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                problemTypes.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            selectedType = it
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val granted = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED

                if (granted) {
                    val uri = createImageUri(context)
                    tempUri = uri
                    takePictureLauncher.launch(uri)
                } else {
                    cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
        ) {
            Text("Tomar foto (evidencia)")
        }

        imageUri?.let {
            Spacer(Modifier.height(6.dp))
            Text("Foto guardada ✅")
        }

        Spacer(Modifier.height(12.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                locationPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        ) {
            Text("Obtener ubicación actual")
        }

        address?.let {
            Spacer(Modifier.height(8.dp))
            Text("Ubicación: $it")
        }

        if (latitude != null && longitude != null) {
            Spacer(Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                OsmMapView(
                    context = context,
                    latitude = latitude!!,
                    longitude = longitude!!,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        error?.let {
            Spacer(Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                vm.create(
                    email = email,
                    product = product,
                    type = selectedType,
                    desc = description,
                    imageUri = imageUri,
                    address = address ?: "Dirección no disponible"
                )

                product = ""
                description = ""
                imageUri = null
                latitude = null
                longitude = null
                address = null
            }
        ) {
            Text("Guardar reclamo")
        }

        Spacer(Modifier.height(80.dp))
    }
}
