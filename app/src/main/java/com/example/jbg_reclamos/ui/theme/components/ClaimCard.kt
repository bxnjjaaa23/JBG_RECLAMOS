package com.example.jbg_reclamos.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.jbg_reclamos.data.local.ClaimEntity

@Composable
fun ClaimCard(
    claim: ClaimEntity,
    showOwner: Boolean = false,
    primaryActions: @Composable RowScope.() -> Unit,
    destructiveAction: (@Composable () -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(), // ✨ animación suave
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            // TÍTULO
            Text(
                text = claim.productName,
                style = MaterialTheme.typography.titleMedium
            )

            if (showOwner) {
                Text(
                    text = "👤 ${claim.ownerEmail}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Divider(Modifier.padding(vertical = 6.dp))

            // INFO
            InfoRow("Tipo", claim.problemType)
            InfoRow("Descripción", claim.description)
            InfoRow("Ubicación", claim.address)
            InfoRow("Estado", claim.status)

            // IMAGEN
            claim.imageUri?.let {
                Spacer(Modifier.height(10.dp))
                AsyncImage(
                    model = it,
                    contentDescription = "Evidencia",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
            }

            Spacer(Modifier.height(12.dp))

            // BOTONES PRINCIPALES
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                content = primaryActions
            )

            // BOTÓN DESTRUCTIVO
            destructiveAction?.let {
                Spacer(Modifier.height(10.dp))
                it()
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Text("$label: $value")
    }
}
